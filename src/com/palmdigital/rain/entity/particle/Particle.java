package com.palmdigital.rain.entity.particle;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;

public class Particle extends Entity
{
	private Sprite sprite;
	
	private int life;
	
	protected double xx, yy, xa, ya; 	// xa & ya represent the amount of pixels we move on the x and y axes
										// xx & yy represent 
	
	public Particle(int x, int y, int life)
	{
		this.x = x;
		this.y = y;
		this.xx = x; // x is an integer, but xx is a double
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian();	// random movement in xa and ya
		this.ya = random.nextGaussian();
	}
	
	public void update()
	{
		this.xx += xa;
		this.yy += ya;
	}
	
	public void render(Screen screen)
	{
		screen.renderSprite((int)xx, (int)yy, sprite, true);
	}
}
