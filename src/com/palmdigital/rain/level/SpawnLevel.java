package com.palmdigital.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.level.tile.GrassTile;
import com.palmdigital.rain.level.tile.Tile;

public class SpawnLevel extends Level
{
	public SpawnLevel(String path) 
	{
		super(path);
	}	
	
	protected void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}

	protected void generateLevel()
	{
	}
}
