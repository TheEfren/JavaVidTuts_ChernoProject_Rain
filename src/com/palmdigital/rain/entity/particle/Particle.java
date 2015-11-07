package com.palmdigital.rain.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.palmdigital.rain.entity.Entity;
import com.palmdigital.rain.graphics.Sprite;

public class Particle extends Entity
{
	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;
	
	public Particle(int x, int y, int life, int amount)
	{
		
	}
}
