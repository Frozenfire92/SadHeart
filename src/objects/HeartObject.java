//HeartObject.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is the heart object. It keeps track of happiness level
 * and displays the correct face accordingly.
 */

package objects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import states.Level2State;

public class HeartObject extends GameObject
{
	public double currentHappinessLevel = 0;
	public int minHappinessLevel = 0;
	public int maxHappinessLevel = 100;
	public boolean jumping = false;
	
	private int happinessScale = 6;
	private int numberHeartFrames = 4;
	private int numberFaceFrames = 10;
	private int currentFrame = 0, currentFrameCount = 0;
	private BufferedImage heartset, faceset;
	private BufferedImage[] heartFrames = new BufferedImage[numberHeartFrames];
	private BufferedImage[] faceFrames = new BufferedImage[numberFaceFrames];

	//Constructor
	public HeartObject()
	{
		super();
	}
	
	//Constructor that sets the x and y position
	public HeartObject(int x, int y)
	{
		super(x,y);
	}
	
	/*
	 * Initializes the Heart Object and loads the spritesheets. Will set current happiness
	 * level to the startHappinessLevel parameter. Resets x position and y velocity.
	 */
	public void init(int startHappinessLevel)
	{
		currentHappinessLevel = startHappinessLevel;
		
		yPos = -32 + GamePanel.HEIGHT/2;
		xPos = GamePanel.WIDTH/4;
		yVel = 0;
		
		try
		{
			heartset = ImageIO.read(getClass().getResourceAsStream("/Heart/HeartSheet.png"));
			for (int i = 0; i < numberHeartFrames; i++)
			{
				heartFrames[i] = heartset.getSubimage(64*i, 0, 64, 64);
			}
			
			faceset = ImageIO.read(getClass().getResourceAsStream("/Heart/FaceSheet.png"));
			for (int i = 0; i < numberFaceFrames; i++)
			{
				faceFrames[i] = faceset.getSubimage(64*i, 0, 64, 64);
			}
		}
		catch(IOException e)
		{
			System.out.println("Erorr loading heart");
			e.printStackTrace();
		}
	}

	//Updates the hearts position, happiness level, and animation
	public void update() 
	{
		// Collision with window's boundaries
		if(xPos < 0) xPos = 0;
		if(xPos > GamePanel.WIDTH - 64) xPos = GamePanel.WIDTH - 64;
		if(yPos < 0) yPos = 0;
		if(yPos > GamePanel.HEIGHT - 64) yPos = GamePanel.HEIGHT - 64;
				
		xPos += xVel;
		yPos += yVel;
		
		currentHappinessLevel -= 0.05;
		if(jumping)currentHappinessLevel -= 0.1;
		
		if (currentHappinessLevel > 99) currentHappinessLevel = 99;
		if (currentHappinessLevel < 0) currentHappinessLevel = 0;
		scaleHappiness();
		updateHeartAnimation();
	}
	
	//Updates the wings flapping
	private void updateHeartAnimation()
	{
		currentFrameCount++;
		if(currentFrameCount > 40) currentFrame = 0;
		currentFrame = currentFrameCount / 10;
		if(currentFrame > 3) 
		{
			currentFrame = 0;
			currentFrameCount = 0;
		}
	}

	//Draws the current heart frame and current face frame
	public void draw(Graphics2D g) 
	{
		g.drawImage(heartFrames[currentFrame], xPos, yPos, 64, 64, null);
		g.drawImage(faceFrames[happinessScale], xPos, yPos, 64, 64, null);
	}
	
	//Scales the happiness level to match up with image count
	public void scaleHappiness()
	{
		happinessScale = (int)currentHappinessLevel/10;
	}
	
	//User Input
	public void keyPressed(int k) 
	{
		if (k == KeyEvent.VK_SPACE)
		{
			jumping = true;
			setYVel(-4);
		}
	}
	public void keyReleased(int k) 
	{
		if (k == KeyEvent.VK_SPACE)
		{
			jumping = false;
			setYVel(6);
			if (Level2State.level2) setYVel(8);
		}
	}
}
