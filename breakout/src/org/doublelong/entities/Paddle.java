package org.doublelong.entities;

import org.doublelong.breakout.renderers.PaddleRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle
{
	private final float WIDTH = 1.5f;
	private final float HEIGHT = .25f;

	private final PaddleRenderer renderer;

	private final Vector2 position;
	public Vector2 getPosition() {return this.position;}

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	public Color color = Color.BLUE;

	public Paddle()
	{
		this.position = new Vector2(Board.BOARD_WIDTH / 2 - WIDTH / 2, 1f);
		this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
		this.renderer = new PaddleRenderer(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.render(batch, cam);
	}

	public void update(float delta)
	{

	}
}
