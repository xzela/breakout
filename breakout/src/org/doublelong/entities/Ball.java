package org.doublelong.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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

	private final Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private final ShapeRenderer renderer;

	private final float ballSpeed;

	public Ball(Board board)
	{
		this.board = board;
		this.ballSpeed = SPEED;

		this.setActive(false);
		this.position = this.board.paddle.getBallPosition();
		this.bounds = new Rectangle(this.position.x, this.position.y, SIZE, SIZE);
		this.renderer = new ShapeRenderer();
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
			this.position.add(this.ballSpeed * this.position.x * delta, this.ballSpeed * this.position.y * delta);
			this.bounds.setX(this.position.x);
			this.bounds.setY(this.position.y);
		}
		else // it's not active, it should "attached" to the paddle
		{
			this.position.x = this.board.paddle.getBallPosition().x;
			this.position.y = this.board.paddle.getBallPosition().y;
		}
	}
}
