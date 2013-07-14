package org.doublelong.breakout.screens;

import org.doublelong.breakout.BreakoutGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen
{

	private final BreakoutGame game;
	private final SpriteBatch batch;
	private final OrthographicCamera cam;
	private final BitmapFont font;

	public EndScreen(BreakoutGame game)
	{
		this.game = game;
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.cam = new OrthographicCamera();
	}

	@Override
	public void render(float delta)
	{
		// clear screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		this.renderEndText();
		this.batch.end();
	}

	private void renderEndText()
	{
		this.font.setColor(Color.RED);
		this.font.draw(this.batch, "Game Over", 200f, 200f);
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

}
