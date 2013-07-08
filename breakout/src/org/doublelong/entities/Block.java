package org.doublelong.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block
{
	private final float WIDTH = 1f;
	private final float HEIGHT = .25f;

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private final Vector2 position;
	public Vector2 getPosition() {return this.position; }

	public Block(Vector2 position)
	{
		this.position = position;
		this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
	}
}
