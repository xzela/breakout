package org.doublelong.entities;

import org.doublelong.breakout.BreakoutGame;
import org.doublelong.breakout.renderers.BlockRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Board
{
	public final static float BOARD_WIDTH = 10f;
	public final static float BOARD_HEIGHT = 12f;

	private final BreakoutGame game;
	public final Paddle paddle;
	public final Ball ball;
	public final BlockRenderer blocks;
	public final BlockRenderer walls;


	// not sure if this will do anything
	public final static Rectangle getBounds = new Rectangle(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

	public Board(BreakoutGame game)
	{
		this.game = game;
		this.paddle = new Paddle(this);
		this.ball = new Ball(this);
		this.blocks = new BlockRenderer(this);
		this.walls = new BlockRenderer(this, this.renderWalls());
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.paddle.render(batch, cam);
		this.ball.render(batch, cam);
		this.blocks.render(batch, cam);
		this.walls.render(batch, cam);
	}

	public void update(float delta)
	{
		this.paddle.update(delta);
		this.ball.update(delta);
	}

	private Block[] renderWalls()
	{
		Block[] walls = new Block[3];
		walls[0] = new Block(new Vector2(0,0), Board.BOARD_HEIGHT, .25f); //left wall
		walls[1] = new Block(new Vector2(0, Board.BOARD_HEIGHT - .25f), .25f, Board.BOARD_WIDTH); //top wall
		walls[2] = new Block(new Vector2(Board.BOARD_WIDTH - .25f,0), Board.BOARD_HEIGHT, .25f); //right wall
		return walls;
	}
}
