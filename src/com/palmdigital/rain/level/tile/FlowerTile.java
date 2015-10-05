package com.palmdigital.rain.level.tile;

import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;

public class FlowerTile extends Tile 
{

	public FlowerTile(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen)
	{		
		screen.renderTile(x << 4, y << 4, this);
	}

}
