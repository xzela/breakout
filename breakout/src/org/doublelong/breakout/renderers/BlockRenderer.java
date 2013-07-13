package org.doublelong.breakout.renderers;

import org.doublelong.entities.Block;
import org.doublelong.entities.Board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class BlockRenderer
{
	private final Board board;

	private final Block[] blocks;
	public Block[] getBlocks() {return this.blocks; }

	protected final ShapeRenderer renderer;

	public BlockRenderer(Board board)
	{
		this.board = board;
		this.blocks = generateBlocks(10);
		this.renderer = new ShapeRenderer();
	}

	public BlockRenderer(Board board, Block[] blocks)
	{
		this.board = board;
		this.blocks = blocks;
		this.renderer = new ShapeRenderer();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.setProjectionMatrix(cam.combined);
		this.renderer.begin(ShapeType.FilledRectangle);
		for (Block block : this.blocks)
		{
			if (!block.isDestroyed())
			{
				this.renderer.setColor(block.getColor());
				this.renderer.filledRect(block.getPosition().x,
						block.getPosition().y,
						block.getBounds().width,
						block.getBounds().height);
			}
		}
		this.renderer.end();
	}

	private Block[] generateBlocks(int count)
	{
		float x = 0 + .5f;
		float y = Board.BOARD_HEIGHT - 2f;
		Block[] temp = new Block[count];
		for (int i = 0; i < count; i++)
		{
			if (x + Block.WIDTH > Board.BOARD_WIDTH - .25f)
			{
				x = 0 + .5f;
				y = y - 1;
			}
			Vector2 v = new Vector2(x, y);
			System.out.println(v);
			temp[i] = new Block(v);
			x += Block.WIDTH + .25f;
		}
		return temp;
	}
}
