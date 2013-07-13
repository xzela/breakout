package org.doublelong.entities;

import org.doublelong.breakout.BreakoutGame;
import org.doublelong.breakout.renderers.BlockRenderer;
import org.doublelong.breakout.renderers.WallRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Board
{
	public final static float BOARD_WIDTH = 10f;
	public final static float BOARD_HEIGHT = 12f;

	private final BreakoutGame game;
	public final Paddle paddle;
	public final Ball ball;
	public final BlockRenderer bricks;
	public final WallRenderer walls;
	public int destroyedBricks = 0;

	// not sure if this will do anything
	public final static Rectangle getBounds = new Rectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

	public Board(BreakoutGame game)
	{
		this.game = game;
		this.paddle = new Paddle(this);
		this.ball = new Ball(this);
		this.bricks = new BlockRenderer(this);
		this.walls = new WallRenderer(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.paddle.render(batch, cam);
		this.ball.render(batch, cam);
		this.bricks.render(batch, cam);
		this.walls.render(batch, cam);
	}

	public void update(float delta)
	{
		this.paddle.update(delta);
		this.ball.update(delta);
	}

}
