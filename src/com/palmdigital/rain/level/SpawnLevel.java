package com.palmdigital.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.palmdigital.rain.entity.mob.Chaser;
import com.palmdigital.rain.entity.mob.Dummy;
import com.palmdigital.rain.entity.mob.Shooter;
import com.palmdigital.rain.entity.mob.Star;

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
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		//add(new Chaser(19, 55));
		//add(new Star(17, 35));
		add(new Shooter(20, 45));
		add(new Shooter(20, 55));
		add(new Dummy(15, 52));
		
		for (int i = 0; i < 5; i++) 
		{
			//add(new Dummy(20, 55));
		}
	}

	protected void generateLevel()
	{
	}
}
