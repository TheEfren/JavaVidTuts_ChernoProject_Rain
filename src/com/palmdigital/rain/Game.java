package com.palmdigital.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.palmdigital.rain.entity.mob.Player;
import com.palmdigital.rain.graphics.Screen;
import com.palmdigital.rain.input.Keyboard;
import com.palmdigital.rain.level.Level;
import com.palmdigital.rain.level.RandomLevel;

public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = 168;	// width/16 * 9; 	// 50,400
	public static int scale = 3;
	public static String title = "Rain";	
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, 
			BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game()
	{
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		screen = new Screen(width, height);	
		frame = new JFrame();		
		key = new Keyboard();
		level = new RandomLevel(64, 64);
		player = new Player(key);
		frame.addKeyListener(key);
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join();			
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	// this run method will be called automagically when the Thread in line 15 is created
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; // he says: this converts nanoseconds (nanotime) into milliseconds-- NO! This is wrong!!
						// 1000000000 nanoseconds makes 1 second because a nanosecond is 1e-9 seconds. 
						// So, ns means 1/60th of a second. This will be used to control how fast updates occur. In this case 60 times per second
		double delta = 0.0;
		int frames = 0;
		int updates = 0;	// measures how many times we update per second; this should be 60 at all times
		frame.requestFocus();
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // now - lastTime gives us elapsed time, the time it's taken from the last iteration to this one
			lastTime = now;					// in other words, how quickly we're rendering and updating (if we updated last frame)	
			while(delta >= 1)
			{
				update();	// we'll limit this to 60 times per second; so we won't update as often as we render
				updates++;
				delta--;		
			}
			render();	// display images to screen - unlimited speed
			frames++; 	// f
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void update()
	{
		key.update();
		player.update();
	}	

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width/2;
		int yScroll = player.y - screen.height/2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i++)
		{
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.drawString("X: " + player.x + ", Y: " + player.y, 350, 300);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}	
}