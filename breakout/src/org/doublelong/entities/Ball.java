package org.doublelong.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball
{

	public static final float SIZE = .25f;

	private final Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private final ShapeRenderer renderer;

	public Ball(Vector2 position)
	{
		this.position = position;
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
}
