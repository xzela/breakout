package org.doublelong.entities;

import org.doublelong.breakout.controllers.PaddleController;
import org.doublelong.breakout.renderers.PaddleRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle
{
	private final Board board;

	private final float WIDTH = 1.5f;
	private final float HEIGHT = .25f;
	public final float SPEED = .01f;


	private final PaddleRenderer renderer;
	public final PaddleController controller;

	private final Vector2 position;
	public Vector2 getPosition() {return this.position;}

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	public Color color = Color.BLUE;

	public Paddle(Board board)
	{
		this.board = board;
		this.position = new Vector2(Board.BOARD_WIDTH / 2 - WIDTH / 2, 0.5f);
		this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
		this.renderer = new PaddleRenderer(this);
		this.controller = new PaddleController(this.board);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.render(batch, cam);
	}

	public void update(float delta)
	{
		this.controller.update(delta);
	}

	public Vector2 getBallPosition()
	{
		float x = this.position.x + (this.bounds.width / 2) - Ball.SIZE / 2;
		float y = this.bounds.y + this.bounds.height;
		return new Vector2(x,y);
	}
}
