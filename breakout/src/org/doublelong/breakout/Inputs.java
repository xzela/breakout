package org.doublelong.breakout;

import org.doublelong.breakout.controllers.PaddleController;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Inputs implements InputProcessor
{

	private final PaddleController controller;

	public Inputs(PaddleController controller)
	{
		this.controller = controller;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.LEFT)
		{
			this.controller.leftPressed();
		}
		if (keycode == Keys.RIGHT)
		{
			this.controller.rightPressed();
		}
		if (keycode == Keys.SPACE)
		{
			this.controller.spacePressed();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		if (keycode == Keys.LEFT)
		{
			this.controller.leftReleased();
		}
		if (keycode == Keys.RIGHT)
		{
			this.controller.rightReleased();
		}
		if (keycode == Keys.SPACE)
		{
			this.controller.spaceReleased();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
