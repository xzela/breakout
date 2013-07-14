package org.doublelong.breakout;

import org.doublelong.breakout.screens.BreakoutScreen;
import org.doublelong.breakout.screens.EndScreen;
import org.doublelong.breakout.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class BreakoutGame extends Game
{
	public final int WINDOW_WIDTH = 480;
	public final int WINDOW_HEIGHT = WINDOW_WIDTH / 9 * 10; //huh?
	public final String WINDOW_TITLE = "Breakout Game";

	private BreakoutScreen breakoutScreen;
	private EndScreen endScreen;
	private MenuScreen menuScreen;

	@Override
	public void create()
	{
		this.initilize();
		setScreen(this.menuScreen);
	}

	private void initilize()
	{
		this.menuScreen = new MenuScreen(this);
		this.endScreen = new EndScreen(this);
		this.breakoutScreen = new BreakoutScreen(this);
	}

	public void startBreakoutGame()
	{
		setScreen(this.breakoutScreen);
	}

	public void endBreakoutGame()
	{
		setScreen(this.endScreen);
	}


}
