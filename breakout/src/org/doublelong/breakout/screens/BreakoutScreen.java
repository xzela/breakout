package org.doublelong.breakout.screens;

import org.doublelong.breakout.BreakoutGame;
import org.doublelong.entities.Board;
import org.doublelong.entities.Paddle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BreakoutScreen implements Screen
{
	private final BreakoutGame game;
	private final OrthographicCamera cam;
	private final SpriteBatch batch;

	public final Paddle paddle;

	public BreakoutScreen(BreakoutGame game)
	{
		this.game = game;
		this.batch = new SpriteBatch();
		this.cam = new OrthographicCamera(Board.BOARD_WIDTH, Board.BOARD_HEIGHT);
		this.cam.setToOrtho(false, Board.BOARD_WIDTH, Board.BOARD_HEIGHT);
		this.paddle = new Paddle();
	}

	@Override
	public void render(float delta)
	{
		// clear the screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.paddle.render(this.batch, this.cam);

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
