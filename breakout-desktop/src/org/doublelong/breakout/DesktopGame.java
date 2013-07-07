package org.doublelong.breakout;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame
{
	public static void main(String[] args)
	{
		BreakoutGame game = new BreakoutGame();
		new LwjglApplication(game, game.WINDOW_TITLE, game.WINDOW_WIDTH, game.WINDOW_HEIGHT, false);
	}

}
