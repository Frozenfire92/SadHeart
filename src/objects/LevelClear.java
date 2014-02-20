//LevelClear.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class displays the overlay for when level 1 is beaten. A direct copy of GameOver.java
 */

package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class LevelClear 
{
	BufferedImage go;
	
	//Constructor loads the image
	public LevelClear()
	{
		try 
		{
			go = ImageIO.read(getClass().getResourceAsStream("/GUI/Clear.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	//Draws the overlay when the level is cleared
	public void showLevelClear(Graphics2D g)
	{
		g.drawImage(go, GamePanel.WIDTH/2-100, GamePanel.HEIGHT/2 - 60, 200, 120 ,null);
	}
}