package org.doublelong.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Block
{
	private String name;
	public void setName(String name) { this.name = name;}
	public String getName() { return this.name; }

	public Wall(Vector2 position, float height, float width, String name)
	{
		super(position);
		this.getBounds().setHeight(height);
		this.getBounds().setWidth(width);
		this.name = name;
		this.color = Color.ORANGE;
	}

}
