package com.palmdigital.rain.entity.mob;

import java.util.List;

import com.palmdigital.rain.graphics.AnimatedSprite;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.graphics.SpriteSheet;

public class Chaser extends Mob
{
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;
	
	public Chaser(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	private void move()
	{
		xa = 0; 
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if(players.size() > 0)
		{
			Player player = players.get(0);
			if(x < player.getX()) xa += speed;
			if(x > player.getX()) xa -= speed;
			if(y < player.getY()) ya += speed;
			if(y > player.getY()) ya -= speed;
		}
		
		if(xa != 0 || ya != 0) 
		{
			move(xa, ya);
			walking = true;
		}
		else
			walking = false;
	}

	public void update() 
	{		
		move();
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
	}
	

	public void render(Screen screen) 
	{
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int)(y - 16), this);
	}

}
