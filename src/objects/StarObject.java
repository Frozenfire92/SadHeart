//StarObject.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is the star object. It keeps track when a star has collided
 * with player. Also will determine spacing between stars.
 */

package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class StarObject extends GameObject
{
	private Random random;
	
	private BufferedImage starsheet;
	public BufferedImage currentFrame;
	public BufferedImage[] starFrames = new BufferedImage[8];
	public int starSpacing = 180;
	public boolean hasCollided;

	//Constructor that sets the spacing betwee stars. X is for star #
	public StarObject (int x, int offset)
	{
		hasCollided = false;
		//Hide the initialization draw call
		xPos = GamePanel.WIDTH;
		starSpacing = offset;
		starSpacing *= x;
		random = new Random();
		
		//Load the spritesheet into BufferedImages
		try
		{
			starsheet = ImageIO.read(getClass().getResourceAsStream("/Crystals/StarSheet.png"));
			for(int i=0;i<starFrames.length;i++)
			{
				starFrames[i] = starsheet.getSubimage(64*i, 0, 64, 64);
			}
			currentFrame = starFrames[0];
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("ERROR LOADING STAR IMAGES");
		}
	}

	//Initializes the star to the correct spacing and sets the default velocity
	public void init()
	{
		resetStar(starSpacing);
		xVel = -1;
	}

	//Updates the stars position based on velocity
	public void update() 
	{
		xPos += xVel;
	}

	//Draws the star
	public void draw(Graphics2D g) 
	{
		g.drawImage(currentFrame, xPos, yPos, 32,32, null);
	}
	
	//Set the star's position and speed
	public void setStar(int x1,int y1,int x2,int y2)
	{
		xPos = x1;
		yPos = y1;
		xVel = x2;
		yVel = y2;
	}
	
	//Reset star to default values
	public void resetStar(int o)
	{
		resetxPos(o);
		setFrame();
		setHeight();
		hasCollided = false;
	}
	
	//Reset the star's x position
	public void resetxPos(int o)
	{
		int offset = o;
		xPos = GamePanel.WIDTH - offset;
	}

	//Set the star's x position
	public void setxPos(int x)
	{
		xPos = x;
	}
	
	//Reset the star to a random frame in the spritesheet
	private void setFrame()
	{
		currentFrame = starFrames[random.nextInt(7) + 1];
	}

	//Randomize the star's height and return the value
	public int getHeight()
	{
		setHeight();
		return yPos;
	}
	
	//Set the star's height to a random value
	public void setHeight()
	{
		yPos = random.nextInt(GamePanel.HEIGHT - 64) + 16;
	}
	
	//Set the star's x velocity
	public void setxVel(int i)
	{
		xVel = i;
		if(xVel > -1) xVel = -1;
	}
}