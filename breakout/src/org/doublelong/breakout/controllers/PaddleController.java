package org.doublelong.breakout.controllers;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.entities.Block;
import org.doublelong.entities.Board;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class PaddleController
{
	enum Keys { LEFT, RIGHT, SPACE }
	static Map<Keys, Boolean> keys = new HashMap<PaddleController.Keys, Boolean>();
	static
	{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.SPACE, false);
	}

	private static final float DAMP = .915f;
	private final float MAX_VEL = 4f;
	private final float ACCELERATION = 40f;

	private final Board board;

	private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject()
		{
			return new Rectangle();
		}
	};

	public PaddleController(Board board)
	{
		this.board = board;
	}

	public void update(float delta)
	{
		// process user inputs
		this.processInput(delta);

		// paddle can't move up (yet). set y to 0
		this.board.paddle.getAcceleration().y = 0f;

		// transform acceleration into "frame-time"
		this.board.paddle.getAcceleration().mul(delta);

		// add the current paddle acceleration to the velocity
		this.board.paddle.getVelocity().add(this.board.paddle.getAcceleration().x, this.board.paddle.getAcceleration().y);

		// check for collidibles
		this.collides(delta);

		// dampen the x velocity (to add that slowing down effect)
		this.board.paddle.getVelocity().x *= DAMP;

		// test for MAX velocities:
		if (this.board.paddle.getVelocity().x > MAX_VEL)
		{
			this.board.paddle.getVelocity().x = MAX_VEL;
		}
		if (this.board.paddle.getVelocity().x < -MAX_VEL)
		{
			this.board.paddle.getVelocity().x = -MAX_VEL;
		}
	}

	/**
	 * 
	 * @param delta
	 * @return
	 */
	public boolean collides(float delta)
	{
		// transform velocity to "frame-time"
		this.board.paddle.getVelocity().mul(delta);
		Rectangle r = rectPool.obtain();
		r.set(this.board.paddle.getBounds());
		r.x += this.board.paddle.getVelocity().x;

		Block[] blocks = this.board.walls.getBlocks();
		for (Block block : blocks)
		{
			// find a block that might overlap the bounder
			if (r.overlaps(block.getBounds()))
			{
				this.board.paddle.getVelocity().x = 0f;
				return true;
			}
		}
		r.x = this.board.paddle.getPosition().x;
		this.board.paddle.getPosition().add(this.board.paddle.getVelocity());
		this.board.paddle.getBounds().setX(this.board.paddle.getPosition().x);
		this.board.paddle.getBounds().setY(this.board.paddle.getPosition().y);

		// transform velocity back to base units, don't have this things will break
		this.board.paddle.getVelocity().mul(1 / delta);

		return false;
	}

	public void processInput(float delta)
	{
		// left key is pressed?
		if (keys.get(Keys.LEFT))
		{
			if (this.board.paddle.getPosition().x > 0)
			{
				this.board.paddle.getAcceleration().x -= ACCELERATION;
			}
			else
			{
				// no longer sure if this works any more
				// reset to zero if it's less than the board width
				this.board.paddle.getPosition().x = 0;
			}
		}

		if (keys.get(Keys.RIGHT))
		{
			if (this.board.paddle.getPosition().x < Board.BOARD_WIDTH - this.board.paddle.getBounds().width)
			{
				this.board.paddle.getAcceleration().x += ACCELERATION;
			}
			else
			{
				// no longer sure if this works any more
				this.board.paddle.getPosition().x = Board.BOARD_WIDTH - this.board.paddle.getBounds().width;
			}
		}

		if (keys.get(Keys.SPACE))
		{
			if (!this.board.ball.isActive())
			{
				this.board.ball.setActive(true);
				this.board.ball.getPosition().y = 1f;
			}
		}
	}

	public void leftPressed() { keys.get(keys.put(Keys.LEFT, true)); }
	public void leftReleased() { keys.get(keys.put(Keys.LEFT, false)); }

	public void rightPressed() { keys.get(keys.put(Keys.RIGHT, true)); }
	public void rightReleased() { keys.get(keys.put(Keys.RIGHT, false)); }

	public void spacePressed() { keys.get(keys.put(Keys.SPACE, true)); }
	public void spaceReleased() { keys.get(keys.put(Keys.SPACE, false)); }
}
