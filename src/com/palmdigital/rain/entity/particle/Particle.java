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
		
		this.xa = random.nextGaussian();	// random movement in xa and ya
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
		
		move(xx + xa, (yy + ya) + (zz + za));
		
	}
	
	private void move(double x, double y) 
	{
		if(collision(x, y))
		{
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;	
		this.zz += za;
	}
	
	public boolean collision(double x, double y)
	{
		boolean solid = false;
		for(int c = 0; c < 4; c++)
		{
			// the width of the collision area is defined by the number after the '*' & number after the third '+' or '-' 
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) 
			{
				ix = (int) Math.floor(xt);
			}
			if(c / 2 == 0)
			{
				iy = (int) Math.floor(yt);
			}
			if(level.getTile(ix, iy).solid())
				solid = true;
		}
		
		return solid;
	}

	public void render(Screen screen)
	{
		screen.renderSprite((int)xx - 1, (int)yy - (int)zz - 1, sprite, true);
	}
}
