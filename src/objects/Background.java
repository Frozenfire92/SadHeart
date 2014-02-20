//Background.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is a background object for any
 * level state to use.
 */

package objects;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background
{
	int happinessScale;
	private int numberBackgroundFrames = 11;
	private BufferedImage bg;
	private BufferedImage[] bgFrames = new BufferedImage[numberBackgroundFrames];
	private int pos;
	public int vel;
	public boolean spedUp = false;
	public int rawHappiness;
	public boolean isSingleImage = false;
	
	public Font smallFont = new Font("Verdana",Font.PLAIN, 12);
	
	/*
	 * Constructor to create a background from a sheet
	 * that has opacity levels included.
	 */
	public Background(String fileName)
	{
		vel = -1;
		try 
		{
			bg = ImageIO.read(getClass().getResourceAsStream(fileName));
			for(int i = 0;i < numberBackgroundFrames; i++)
			{
				bgFrames[10 - i] = bg.getSubimage(0, 480*i, 800, 480);
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		isSingleImage = false;
	}
	
	//Constructor for a static background
	public Background(boolean noOpacity, String filename)
	{
		if (noOpacity)
		{
			try 
			{
				bg = ImageIO.read(getClass().getResourceAsStream(filename));
				isSingleImage = true;
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Initializes the background to current happiness level
	 * and default scroll speed
	 */
	public void init(double currentHappinessLevel)
	{
		rawHappiness = (int)currentHappinessLevel;
		scaleHappiness();
		vel = -1;
		pos = 0;
	}
	
	//Scales the happiness by 1/10 to line up with background images
	private void scaleHappiness()
	{
		happinessScale = rawHappiness/10;
	}
	
	//Updates the current happiness level and current background
	public void update(double currentHappinessLevel)
	{
		rawHappiness = (int)currentHappinessLevel;
		scaleHappiness();
		pos += vel;
	}
	
	//Sets the scroll speed (velocity) of the background
	public void setVel(int newVel)
	{
		vel = newVel;
	}
	
	//Returns the current scroll speed (velocity) of the background
	public int getVel()
	{
		return vel;
	}
	
	//Draws the background based on happiness level
	public void draw(Graphics2D g)
	{
		if (isSingleImage) 
		{
			g.drawImage(bg, pos, 0, GamePanel.WIDTH-vel, GamePanel.HEIGHT, null);
			
			if (pos < 0)
			{
				pos = 0 + GamePanel.WIDTH;
				g.drawImage(bg, pos + GamePanel.WIDTH, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			}
			else if (pos > 0)
			{
				g.drawImage(bg, pos - GamePanel.WIDTH, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			}
		}
		else			
		{
			g.drawImage(bgFrames[happinessScale], pos, 0, GamePanel.WIDTH-vel, GamePanel.HEIGHT, null);
			if (pos < 0)
			{
				pos = 0 + GamePanel.WIDTH;
				g.drawImage(bgFrames[happinessScale], pos + GamePanel.WIDTH, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			}
			else if (pos > 0)
			{
				g.drawImage(bgFrames[happinessScale], pos - GamePanel.WIDTH, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			}
		}
	}
}