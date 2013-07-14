package org.doublelong.entities;

import org.doublelong.breakout.BreakoutGame;
import org.doublelong.breakout.renderers.BlockRenderer;
import org.doublelong.breakout.renderers.WallRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board
{
	public final static float BOARD_WIDTH = 10f;
	public final static float BOARD_HEIGHT = 12f;

	// Entities
	public final BreakoutGame game;
	public final Paddle paddle;
	public final Ball ball;
	public final BlockRenderer bricks;
	public final WallRenderer walls;

	// other
	public int destroyedBricks = 0;

	//local properties
	private final SpriteBatch batch;
	private final BitmapFont font;

	// Player Board properties
	public int balls = 3;
	public int player_score = 0;

	public Board(BreakoutGame game)
	{
		this.game = game;
		this.paddle = new Paddle(this);
		this.ball = new Ball(this);
		this.bricks = new BlockRenderer(this);
		this.walls = new WallRenderer(this);

		// local properties
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.paddle.render(batch, cam);
		this.ball.render(batch, cam);
		this.bricks.render(batch, cam);
		this.walls.render(batch, cam);

		this.batch.begin();
		this.renderPlayerBalls();
		this.renderPlayerScore();
		this.batch.end();
	}

	public void update(float delta)
	{
		this.paddle.update(delta);
		this.ball.update(delta);
	}


	private void renderPlayerBalls()
	{
		this.font.setColor(Color.GRAY);
		this.font.draw(this.batch, "Balls: " + this.balls, 20f, 20f);
	}

	private void renderPlayerScore()
	{
		this.font.setColor(Color.GRAY);
		this.font.draw(this.batch, "Score: " + this.player_score, 350f, 20f);
	}
}
