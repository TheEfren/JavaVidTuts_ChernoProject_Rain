package com.palmdigital.rain.entity.mob;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.graphics.Sprite;

public abstract class Mob extends Entity 
{
	protected Sprite sprite;
	protected int dir = 0; // normal direction conventions: 0 is north, 1 is east, 2 is south, 3 is west
	protected boolean moving = false;
	
	public void move(int xa, int ya) // xa = how the x position changes on the x-axis, ya = how the y position changes on the y-axis
	{
		// into xa & ya, we actually plug in -1 (left or down), 0 (no change in position), 1 (right or up)
		// btw, we only handle moving if there's no collision
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision())
		{
			x += xa; 
			y += ya;
		}
	}
	
	public void update()
	{
		
	}
	
	private boolean collision()
	{
		return false;
	}
	
	public void render()
	{
		
	}
	
	
}
