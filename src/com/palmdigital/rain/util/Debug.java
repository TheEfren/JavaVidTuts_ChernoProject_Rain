package com.palmdigital.rain.util;

import com.palmdigital.rain.graphics.Screen;

public class Debug 
{
	// this forces Debug to be a static class (by making the constructor private)
	// i.e. it will never now be allowed to be instantiated
	private Debug()
	{}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed)
	{
		drawRect(screen, x, y, width, height, 0xff0000, fixed);
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, int col, boolean fixed)
	{
		screen.drawRect(x, y, width, height, col, fixed);
		
	}
}
