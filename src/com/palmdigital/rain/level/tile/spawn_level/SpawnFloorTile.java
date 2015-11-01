package com.palmdigital.rain.level.tile.spawn_level;

import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.level.tile.Tile;

public class SpawnFloorTile extends Tile 
{

	public SpawnFloorTile(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen)
	{		
		screen.renderTile(x << 4, y << 4, this);
	}

}
