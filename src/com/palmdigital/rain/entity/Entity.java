package com.palmdigital.rain.entity;

import java.util.Random;

import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.level.Level;

public abstract class Entity 
{
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update()
	{		
	}
	
	public void render(Screen screen)
	{		
	}
	
	public void remove()
	{
		// remove from level
		removed = true;
	}
	
	public boolean isRemoved()
	{
		return removed;
	}
	
}
