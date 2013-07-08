package org.doublelong.breakout.renderers;

import org.doublelong.entities.Paddle;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PaddleRenderer
{
	private final Paddle paddle;
	private final ShapeRenderer renderer;

	public PaddleRenderer(Paddle paddle)
	{
		this.paddle = paddle;
		this.renderer = new ShapeRenderer();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.FilledRectangle);
		this.renderer.setColor(this.paddle.color);
		this.renderer.filledRect(this.paddle.getPosition().x,
				this.paddle.getPosition().y,
				this.paddle.getBounds().width,
				this.paddle.getBounds().height);
		this.renderer.end();
	}
}
