package com.palmdigital.rain.entity.particle;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;

public class Particle extends Entity
{
	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz; 	// xx & yy represent double version of the x & y ints	 	
	protected double xa, ya, za;	// xa & ya represent the amount of pixels we move on the x and y axes	
	
	
	
	public Particle(int x, int y, int life)
	{
		this.x = x;
		this.y = y;
		this.xx = x; // x is an integer, but xx is a double
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian() + 1.8;	// random movement in xa and ya
		if(this.xa < 0) xa = 0.1;
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public void update()
	{
		time++;
		if(time >= 7400) time = 0;		
		if(time > life) remove();
		za -= 0.1;
		
		if(zz <= 0)
		{
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		
		this.xx += xa;
		this.yy += ya;	
		this.zz += za;
	}
	
	public void render(Screen screen)
	{
		screen.renderSprite((int)xx - 12, (int)yy - (int)zz, sprite, true);
	}
}
