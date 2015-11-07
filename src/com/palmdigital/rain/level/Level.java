package com.palmdigital.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.entity.Spawner;
import com.palmdigital.rain.entity.particle.Particle;
import com.palmdigital.rain.entity.projectile.Projectile;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.level.tile.Tile;

public class Level 
{
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	// constructor for randomly-generated level
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();		
	}
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();	
		
		add(new Spawner(16 * 16, 62 * 16, Spawner.Type.PARTICLE, 50000, this));
	}

	protected void generateLevel() 
	{		
	}
	
	protected void loadLevel(String path)
	{
		
	}
	
	public void update()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update();
		}
		
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).update();
		}
		
		for(int i = 0; i < particles.size(); i++)
		{
			particles.get(i).update();
		}
	}
	
	public List<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	private void time()
	{		
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size)
	{
		boolean solid = false;
		for(int c = 0; c < 4; c++)
		{
			// the width of the collision area is defined by the number after the '*' & number after the third '+' or '-' 
			int xt = (((int)x + (int)xa) + c % 2 * size * 2 - 12) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size + 2) / 16;
			if(getTile(xt, yt).solid())
				solid = true;
		}
		
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++)
		{
			for(int x = x0; x < x1; x++)
			{
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(screen);
		}
		
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).render(screen);
		}
		
		for(int i = 0; i < particles.size(); i++)
		{
			particles.get(i).render(screen);
		}
	}
	
	public void add(Entity e)
	{
		e.init(this);
		if(e instanceof Particle)
		{
			particles.add((Particle) e);
		}
		else if(e instanceof Projectile)
		{
			projectiles.add((Projectile) e);
		}
		else
		{
			entities.add(e);
		}
	}

	// Grass 	= 0xff00ff00
	// Flower 	= 0xffffff00 
	// Rock		= 0xff7f7f00
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if(tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if(tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if(tiles[x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if(tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if(tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.voidTile;
	}
}
