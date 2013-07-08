package org.doublelong.breakout;

import org.doublelong.breakout.screens.BreakoutScreen;

import com.badlogic.gdx.Game;

public class BreakoutGame extends Game
{
	public final int WINDOW_WIDTH = 480;
	public final int WINDOW_HEIGHT = WINDOW_WIDTH / 9 * 10; //huh?
	public final String WINDOW_TITLE = "Breakout Game";

	private BreakoutScreen breakoutScreen;

	@Override
	public void create()
	{
		breakoutScreen = new BreakoutScreen(this);
		setScreen(breakoutScreen);
	}

}
