package com.palmdigital.rain.entity.mob;

import com.palmdigital.rain.graphics.AnimatedSprite;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.graphics.SpriteSheet;

public class Dummy extends Mob
{
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	public Dummy(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	public void update() 
	{
		int xa = 0;
		int ya = 0;
		ya++;
		//ya--;
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		if(ya < 0) 
		{
			animSprite = up;
			dir = Direction.UP;
		}
		else if(ya > 0) 
		{
			animSprite = down;
			dir = Direction.DOWN;
		}
		
		if(xa < 0) 
		{
			animSprite = left;
			dir = Direction.LEFT;
		}
		else if(xa > 0)
		{
			animSprite = right;
			dir = Direction.RIGHT;
		}
		
		if(xa != 0 || ya != 0) 
		{
			move(xa, ya);
			walking = true;
		}
		else
			walking = false;
	}

	public void render(Screen screen) 
	{
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, sprite, 0);
	}
	
}
