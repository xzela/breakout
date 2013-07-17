package org.doublelong.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Ball
{
	private final Board board;

	public enum States { ACTIVE }
	public static Map<States, Boolean> states = new HashMap<Ball.States, Boolean>();
	static {states.put(States.ACTIVE, false);}
	public Boolean isActive() { return states.get(States.ACTIVE);}
	public void setActive(Boolean b) {states.put(States.ACTIVE, b);}

	public static final float SIZE = .25f;
	private static final float SPEED = 2f;
	private float max_vel = 5f;
	private final float ACCELERATION = 40f;

	private final Vector2 velocity = new Vector2();
	public Vector2 getVelocity() { return this.velocity; }

	private final Vector2 acceleration = new Vector2();
	public Vector2 getAcceleration() { return this.acceleration; }

	private final Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private final float ballSpeed;
	public float getBallSpeed() { return this.ballSpeed; }

	private int direction_y = 1;
	private int direction_x = 1;

	private final ShapeRenderer renderer;
	private final Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/soccer.bounce.mp3"));

	private final SpriteBatch batch;
	private final BitmapFont font;

	private boolean debug = false;

	private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject()
		{
			return new Rectangle();
		}
	};

	public Ball(Board board, boolean debug)
	{
		this.board = board;
		this.ballSpeed = SPEED;
		this.debug = debug;

		this.setActive(false);
		this.position = this.board.paddle.getBallPosition();
		this.bounds = new Rectangle(this.position.x, this.position.y, SIZE, SIZE);
		this.renderer = new ShapeRenderer();


		// local debugging properties

		this.batch = new SpriteBatch();
		this.font = new BitmapFont();

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.FilledRectangle);

		this.renderer.setColor(Color.GREEN);
		this.renderer.filledRect(this.position.x, this.position.y, this.bounds.height, this.bounds.width);

		this.renderer.end();
	}

	public void update(float delta)
	{
		// if ball is active, move it around
		if(this.isActive())
		{
			// check for collisions!
			this.acceleration.mul(delta);
			this.collides(delta);

			this.velocity.add(this.acceleration.x, this.acceleration.y);
			this.capVelocity();
			// has the ball gone off the screen, (dropped from the bottom?)
			if (this.position.y < 0 || this.position.y > Board.BOARD_HEIGHT)
			{
				// remove a ball from the player
				this.board.balls -= 1;
				if (this.board.balls < 0)
				{
					// set the end screen
					this.board.game.endBreakoutGame();
				}
				else
				{
					this.reset();
				}

			}
		}
		else // it's not active, it should "attached" to the paddle
		{
			this.position.x = this.board.paddle.getBallPosition().x;
			this.position.y = this.board.paddle.getBallPosition().y;
			this.bounds.setX(this.position.x);
			this.bounds.setY(this.position.y);
		}
		if (this.debug)
		{
			this.debugBall();
		}

	}

	public void reset()
	{
		this.setActive(false);

		this.getPosition().x = this.board.paddle.getBallPosition().x;
		this.getPosition().y = this.board.paddle.getBallPosition().y;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
		this.velocity.x = 0f;
		this.velocity.y = 0f;
		this.acceleration.x = 0f;
		this.acceleration.y = 0f;
		this.max_vel = 5f;

	}

	private void collides(float delta)
	{
		this.velocity.mul(delta);
		Rectangle r = rectPool.obtain();

		r.set(this.bounds);

		r.x += this.velocity.x;

		// bounce off walls
		for (Wall wall : this.board.walls.getBlocks())
		{
			if (r.overlaps(wall.getBounds()))
			{
				// walls have names, bricks don't
				if (wall.getName().equals("left") || wall.getName().equals("right"))
				{
					this.direction_x *= -1;
					this.velocity.x = -this.velocity.x;
					this.acceleration.x = this.direction_x * 1f;
				}
				if (wall.getName().equals("top"))
				{
					this.direction_y *= -1;
					this.velocity.y = -this.velocity.y;
					this.acceleration.y = this.direction_y * 1f;
				}
				break;
			}
			else
			{
				this.acceleration.x = this.direction_x * 1f;
				this.acceleration.y = this.direction_y * 1f;
			}
		}

		// detected brick collisions
		for (Block brick : this.board.bricks.getBlocks())
		{
			if (r.overlaps(brick.getBounds()) && !brick.isDestroyed())
			{
				brick.setDestroyed(true);
				this.max_vel += .5f;
				this.board.player_score += 100 * this.max_vel;

				this.sound.play(1f);

				this.board.destroyedBricks = this.board.destroyedBricks + 1;
				this.direction_x *= 1;
				this.acceleration.x = this.direction_x * this.velocity.x;

				this.direction_y *= -1;
				this.velocity.y = -this.velocity.y;
				this.acceleration.y += this.direction_y;

			}
			else
			{
				this.acceleration.x = this.direction_x * this.ballSpeed;
				this.acceleration.y = this.direction_y * this.ballSpeed;
			}
		}

		if (r.overlaps(this.board.paddle.getBounds()))
		{

			this.direction_y *= -1f;
			this.velocity.y = -this.velocity.y;
			this.acceleration.y = this.direction_y * 1f;

			if (this.board.destroyedBricks == this.board.bricks.getBlocks().length)
			{
				this.reset();
				this.board.game.endBreakoutGame();
			}
		}

		r.x = this.position.x;
		r.y = this.position.y;
		this.position.add(this.velocity);
		this.bounds.setX(this.position.x);
		this.bounds.setY(this.position.y);

		this.velocity.mul(1 / delta);
	}

	private void capVelocity()
	{
		if(this.velocity.y > this.max_vel)
		{
			this.velocity.y = this.max_vel;
		}
		if(this.velocity.y < -this.max_vel)
		{
			this.velocity.y = -this.max_vel;
		}
		if(this.velocity.x > this.max_vel)
		{
			this.velocity.x = this.max_vel;
		}
		if(this.velocity.x < -this.max_vel)
		{
			this.velocity.x = -this.max_vel;
		}
	}

	private void debugBall()
	{
		this.batch.begin();
		this.font.setColor(Color.WHITE);
		this.font.draw(this.batch, "Ball: ", 20f, 120f);
		this.font.draw(this.batch, "velocity: " + this.velocity, 20f, 100f);
		this.font.draw(this.batch, "position: " + this.position, 20f, 80f);
		this.font.draw(this.batch, "acceleration: " + this.acceleration, 20f, 60f);
		this.batch.end();
	}
}
