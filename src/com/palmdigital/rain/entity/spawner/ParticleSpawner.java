package com.palmdigital.rain.entity.spawner;

import com.palmdigital.rain.entity.particle.Particle;
import com.palmdigital.rain.entity.spawner.Spawner.Type;
import com.palmdigital.rain.level.Level;

public class ParticleSpawner extends Spawner
{
	private int life;
	
	public ParticleSpawner(int x, int y, int life, int amount, Level level) 
	{
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for(int i = 0; i < amount; i++)
		{
			level.add(new Particle(x, y, life));
		}
	}

}
