package org.doublelong.breakout.controllers;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.entities.Board;

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

	private final Board board;

	public PaddleController(Board board)
	{
		this.board = board;
	}

	public void update(float delta)
	{
		this.processInput(delta);
	}

	public void processInput(float delta)
	{
		// left key is pressed?
		if (keys.get(Keys.LEFT))
		{
			if (this.board.paddle.getPosition().x > 0)
			{
				this.board.paddle.getPosition().x -= 0.05f;
			}
			else
			{
				//reset to zero if it's less than the board width
				this.board.paddle.getPosition().x = 0;
			}
		}

		if (keys.get(Keys.RIGHT))
		{
			if (this.board.paddle.getPosition().x < Board.BOARD_WIDTH - this.board.paddle.getBounds().width)
			{
				this.board.paddle.getPosition().x += 0.05f;
			}
			else
			{
				this.board.paddle.getPosition().x = Board.BOARD_WIDTH - this.board.paddle.getBounds().width;
			}
		}

		if (keys.get(Keys.SPACE))
		{
			if (!this.board.ball.isActive())
			{
				this.board.ball.setActive(true);
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
