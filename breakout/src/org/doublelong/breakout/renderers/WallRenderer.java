package org.doublelong.breakout.renderers;

import org.doublelong.entities.Board;
import org.doublelong.entities.Wall;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class WallRenderer extends BlockRenderer
{

	private final Wall[] blocks;
	@Override
	public Wall[] getBlocks() {return this.blocks; }

	public WallRenderer(Board board)
	{
		super(board);
		this.blocks = this.generateWalls();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.FilledRectangle);
		for (Wall wall : this.blocks)
		{
			this.renderer.setColor(wall.getColor());
			this.renderer.filledRect(wall.getPosition().x,
					wall.getPosition().y,
					wall.getBounds().width,
					wall.getBounds().height);
		}
		this.renderer.end();
	}

	private Wall[] generateWalls()
	{
		Wall[] walls = new Wall[3];
		walls[0] = new Wall(new Vector2(0,0), Board.BOARD_HEIGHT, .25f, "left"); //left wall
		walls[1] = new Wall(new Vector2(0, Board.BOARD_HEIGHT - .25f), .25f, Board.BOARD_WIDTH, "top"); //top wall
		walls[2] = new Wall(new Vector2(Board.BOARD_WIDTH - .25f,0), Board.BOARD_HEIGHT, .25f, "right"); //right wall
		return walls;
	}
}
