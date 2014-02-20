//HappinessBarObject.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class displays the corresponding happiness bar
 * based on the current happiness level
 */

package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class HappinessBarObject extends GameObject
{
	public int happinessRaw = 100, happinessScale;
	public int numberHappinessFrames = 51;
	public BufferedImage happinessbarsheet;
	public BufferedImage[] happinessFrames = new BufferedImage[numberHappinessFrames];
	
	//Constructor loads the images
	public HappinessBarObject()
	{
		try
		{
			happinessbarsheet = ImageIO.read(getClass().getResourceAsStream("/GUI/HappinessBarSheet.png"));
			for(int i = 0;i < numberHappinessFrames; i++)
			{
				happinessFrames[i] = happinessbarsheet.getSubimage(0,48*i,400, 48);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Initializes to current happiness level
	public void init(double currentHappinessLevel)
	{
		happinessRaw = (int)currentHappinessLevel;
	}

	//Updates based on current happiness level
	public void update(double currentHappinessLevel) 
	{
		happinessRaw = (int)currentHappinessLevel;
	}

	//Scales the happiness level because there are only 51 images
	private void getHappinessScale()
	{
		happinessScale = happinessRaw / 2;
		if (happinessScale > 50) happinessScale = 50;
		if (happinessScale < 0) happinessScale = 0;
	}
	
	//Draws the happiness bar
	public void draw(Graphics2D g) 
	{
		getHappinessScale();
		g.drawImage(happinessFrames[happinessScale], GamePanel.WIDTH -200, 0,400/GamePanel.SCALE,48/GamePanel.SCALE,null);
	}
	
	//Unused because its ovveridden above. Must be in here because it implements the abstract parent class GameObject
	public void update() {}
}