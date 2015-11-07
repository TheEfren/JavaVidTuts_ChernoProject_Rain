package com.palmdigital.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.entity.particle.Particle;
import com.palmdigital.rain.entity.projectile.Projectile;
import com.palmdigital.rain.entity.projectile.WizardProjectile;
import com.palmdigital.rain.graphics.Sprite;

public abstract class Mob extends Entity 
{
	protected Sprite sprite;
	protected int dir = 0; // normal direction conventions: 0 is north, 1 is east, 2 is south, 3 is west
	protected boolean moving = false;
	
	public void move(int xa, int ya) // xa = how the x position changes on the x-axis, ya = how the y position changes on the y-axis
	{
		// look in subclass to see how, into xa & ya, we actually plug in -1 (left or down), 0 (no change in position), 1 (right or up)
		// btw, we only handle moving if there's no collision
		
		if(xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya))
		{
			x += xa; 
			y += ya;
		}
	}
	
	public void update()
	{
		
	}
	
	protected void shoot(int x, int y, double dir)
	{
		//dir *= 180/Math.PI;
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);
		
	}
	
	private boolean collision(int xa, int ya)
	{
		boolean solid = false;
		for(int c = 0; c < 4; c++)
		{
			// the width of the collision area is defined by the number after the '*' & number after the third '+' or '-' 
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if(level.getTile(xt, yt).solid())
				solid = true;
		}
		
		return solid;
	}
	
	public void render()
	{
		
	}	
}
