package org.doublelong.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block
{
	public static final float WIDTH = 2f;
	public static final float HEIGHT = .5f;

	private boolean destroyed = false;
	public boolean isDestroyed() {return this.destroyed; }
	public void setDestroyed(boolean b) { this.destroyed = b; }

	protected Color color;
	public Color getColor() { return this.color; }

	private final Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private final Vector2 position;
	public Vector2 getPosition() {return this.position; }

	public Block(Vector2 position)
	{
		this.color = Color.RED;
		this.position = position;
		this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
	}
}
