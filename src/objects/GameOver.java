//GameOver.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is for the overlay when you lose a level.
 */

package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class GameOver 
{
	BufferedImage go;
	
	//Constructor loads the image
	public GameOver()
	{
		try 
		{
			go = ImageIO.read(getClass().getResourceAsStream("/GUI/GameOver.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Draw the Overlay
	public void showGameOver(Graphics2D g)
	{
		g.drawImage(go, GamePanel.WIDTH/2-100, GamePanel.HEIGHT/2 - 60, 200, 120 ,null);
	}
}