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

	private final ShapeRenderer renderer;

	public BlockRenderer(Board board)
	{
		this.board = board;
		this.blocks = generateBlocks();
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
			this.renderer.setColor(block.getColor());
			this.renderer.filledRect(block.getPosition().x,
					block.getPosition().y,
					block.getBounds().width,
					block.getBounds().height);
		}
		this.renderer.end();
	}

	private Block[] generateBlocks()
	{
		float y = Board.BOARD_HEIGHT - Block.HEIGHT * 2;
		float x = 0f;
		Block[] temp = new Block[10];

		temp[0] = new Block(new Vector2(0.5f, 12f - .5f));
		temp[1] = new Block(new Vector2(2f, 12f - .5f));
		temp[2] = new Block(new Vector2(3.5f, 12f - .5f));
		temp[3] = new Block(new Vector2(5f, 12f - .5f));
		temp[4] = new Block(new Vector2(6.5f, 12f - .5f));

		temp[5] = new Block(new Vector2(1f, 12f - 1f));
		temp[6] = new Block(new Vector2(2.5f, 12f - 1f));
		temp[7] = new Block(new Vector2(4f, 12f - 1f));
		temp[8] = new Block(new Vector2(5.5f, 12f - 1f));
		temp[9] = new Block(new Vector2(7f, 12f - 1f));

		return temp;
	}
}
