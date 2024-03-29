package com.palmdigital.rain.entity.spawner;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.entity.particle.Particle;
import com.palmdigital.rain.level.Level;

public abstract class Spawner extends Entity
{
	public enum Type
	{
		MOB,
		PARTICLE
	}
	
	private Type type;	
	
	public Spawner(int x, int y, Type type, int amount, Level level)
	{
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
