package com.palmdigital.rain.entity.mob;

import com.palmdigital.rain.Game;
import com.palmdigital.rain.entity.projectile.Projectile;
import com.palmdigital.rain.entity.projectile.WizardProjectile;
import com.palmdigital.rain.graphics.AnimatedSprite;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.graphics.SpriteSheet;
import com.palmdigital.rain.input.Keyboard;
import com.palmdigital.rain.input.Mouse;

public class Player extends Mob 
{	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	Projectile p; // like the gun that the player has
	private int fireRate = 0;
	
	public Player(Keyboard input)
	{
		this.input = input;
		sprite = Sprite.player_forward;
		animSprite = down;
	}
	
	public Player(int x, int y, Keyboard input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update()
	{
		if(walking) animSprite.update();
		else animSprite.setFrame(1);
		if(fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if(anim < 7500) anim++;
		else anim = 0;
		
		if(input.up) 
		{
			ya--;
			animSprite = up;
		}
		else if(input.down) 
		{
			ya++;
			animSprite = down;
		}
		
		if(input.left) 
		{
			xa--;
			animSprite = left;
		}
		else if(input.right)
		{
			xa++;
			animSprite = right;
		}
		
		if(xa != 0 || ya != 0) 
		{
			move(xa, ya);
			walking = true;
		}
		else
			walking = false;
		
		clear();
		updateShooting();
	}
	
	private void clear() 
	{
		for(int i = 0; i < level.getProjectiles().size(); i++)
		{
			if(level.getProjectiles().get(i).isRemoved())
			{
				level.getProjectiles().remove(i);
			}
		}
	}

	private void updateShooting() 
	{
		if(Mouse.getButton() == 1 && fireRate <= 0)
		{
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen)
	{		
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x-16, y-16, sprite, flip);
	}
}
