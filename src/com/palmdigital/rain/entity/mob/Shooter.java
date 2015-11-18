package com.palmdigital.rain.entity.mob;

import java.util.List;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.graphics.AnimatedSprite;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.graphics.Sprite;
import com.palmdigital.rain.graphics.SpriteSheet;
import com.palmdigital.rain.util.Debug;
import com.palmdigital.rain.util.Vector2i;

public class Shooter extends Mob
{
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private int time = 0;
	private int xa = 0, ya = 0;
	
	private Entity rand = null;
	
	
	
	public Shooter(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
		
	}

	public void update() 
	{		
		//time % 60 == 0 // this is true once per second, since this is in the update method that runs at 60 frames/second
		time++;
		if(time % (random.nextInt(50) + 30) == 0)
		{
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if(random.nextInt(4) == 0)
			{
				xa = 0;
				ya = 0;
			}
		}
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
			//move(xa, ya);
			walking = true;
		}
		else
			walking = false;
		
		shootRandom();
	}
	
	private void shootRandom()
	{
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());
		
		if(time % (30 + random.nextInt(91)) == 0) // happens in the range from .5 to 2 seconds
		{			
			// get a random index from the entities within the specified range in getEntities() method
			int index = random.nextInt(entities.size());			
			rand = entities.get(index);		
		}
		
		if(rand != null)
		{			
			double px = rand.getX();
			double py = rand.getY();
			double dx = px - x;
			double dy = py - y;
			double dir = Math.atan2(dy, dx);		
			shoot(x, y, dir);
		}
	}
	
	private void shootClosest()
	{
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());
		
		double min = 0;
		Entity closest = null;
		// this finds the closest entity
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i((int)x, (int)y), new Vector2i((int)e.getX(), (int)e.getY()));
			if(i == 0 || distance < min) 
			{
				min = distance;
				closest = e;	
			}
		}
		
		if(closest != null)
		{			
			double px = closest.getX();
			double py = closest.getY();
			double dx = px - x;
			double dy = py - y;
			double dir = Math.atan2(dy, dx);		
			shoot(x, y, dir);
		}
	}

	public void render(Screen screen) 
	{
		sprite = animSprite.getSprite();
		Debug.drawRect(screen, 17*16, 57*16, 60, 40, true);
		screen.renderMob((int)(x -16), (int)(y - 16), this);
	}
	
}
