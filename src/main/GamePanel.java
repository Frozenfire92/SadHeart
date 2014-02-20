//GamePanel.java
//Sad Heart
//Sarah MacDonald + Joel Kuntz

/*
 * This class contains the main game loop,
 * the dimensions of the window, the graphics and the
 * mouse position.
 */

package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import states.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Game State Manager
	private GameStateManager gsm;
	
	// Panel dimensions
	public static final int WIDTH = 400;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

	// Main Game Loop
	private Thread thread;
	private boolean running = false;
	private int fps = 60;
	private long targetTime = 1000/fps; // target time to take one game loop
	
	// Graphics
	private Graphics2D g;
	private BufferedImage image;

	// Mouse Positions
	public static int mouseX, mouseY;

	/*
	 * Constructor for the panel.
	 * Sets the dimensions of the window, and the focus
	 * of the window to be on this panel
	 */
	public GamePanel()
	{
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	/*
	 * Initializes the GamePanel and GameStateManager.
	 * The graphics will be created for display on this panel
	 * and the GameStateManager will be instantiated.
	 */
	public void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		gsm = new GameStateManager();
	}
	
	/*
	 * Adds the listeners for user input.
	 * Starts a new thread if none is made yet and 
	 * initializes listeners for keyboard and mouse input.
	 */
	public void addNotify()
	{
		super.addNotify();
		
		if (thread == null)
		{
			running = true;
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	/*
	 * This is the main game loop
	 * It will update the game variables,
	 * and draw the images to the screen.
	 */
	public void run()
	{
		init();
		
		//For keeping track of nanoTime
		long start;
		long elapsed;
		long wait;
		
		while(running)
		{
			//The start time of the function call
			start = System.nanoTime();
			
			//update variables, create and draw images
			update(); 
			draw();   
			drawToScreen();  
			
			//set the amount of time the function took
			elapsed = System.nanoTime()-start;

			//pauses the thread to maintain 60 fps
			wait = targetTime-elapsed/1000000;
			if (wait < 0) wait = 5;
			try
			{
				Thread.sleep(wait);	
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Calls the update function from the gamestate manager
	 * which calls the update function of the current state
	 */
	public void update()
	{
		gsm.update();
	}
	
	/*
	 * Clears the panel and then calls the GameStateManager to
	 * create the graphics.
	 */
	public void draw()
	{
		g.clearRect(0, 0, WIDTH, HEIGHT);
		gsm.draw(g);
	}
	
	/*
	 * Gets the graphics that were drawn, then displays them on the panel.
	 */
	public void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}
	    
	/*
	 * User Input passed to the GameStateManager
	 */
	public void keyPressed(KeyEvent k)
	{
		gsm.keyPressed(k.getKeyCode());
	}
	
	public void keyReleased(KeyEvent k)
	{
		gsm.keyReleased(k.getKeyCode());
	}
	
	public void mousePressed(MouseEvent e)
	{
		gsm.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e)
	{
		gsm.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX() / SCALE;
		mouseY = e.getY() / SCALE;
	}
	
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX() / SCALE;
		mouseY = e.getY() / SCALE;
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}
	public void keyTyped(KeyEvent k){}
}