package com.palmdigital.rain.entity.projectile;

import com.palmdigital.rain.entity.spawner.ParticleSpawner;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;

public class WizardProjectile extends Projectile
{
	public static final int FIRE_RATE = 10;	// higher FIRE_RATE is slower
	
	public WizardProjectile(double x, double y, double dir) 
	{
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update()
	{
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 7, 5, 5)) 
		{	
			level.add(new ParticleSpawner((int)x, (int)y, 44, 50, level));
			remove();
		}
		move();
	}
	
	protected void move()
	{
		x += nx;
		y += ny;
		
		if(distance() > range)
		{
			remove();
		}
	}
	
	private double distance() 
	{
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen)
	{
		screen.renderProjectile((int)x - 8, (int)y - 2, this);
	}
	
}
