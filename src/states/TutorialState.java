//TutorialState.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is for the tutorial state.
 */

package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.GamePanel;
import objects.Background;
import objects.HeartObject;
import objects.StarObject;

public class TutorialState extends GameState
{
	//Font
	public Font smallFont = new Font("Verdana",Font.PLAIN, 12);
	
	//GameObjects
	public HeartObject heart;
	public Background bg;
	public ArrayList<StarObject> stars = new ArrayList<StarObject>();
	private int numOfStars = 100;
	
	int originalPos = -1;

	//Checkpoints
	private int spaceCount;
	boolean jumpPressed = false;
	private boolean checkpoint1 = true;
	private boolean checkpoint2 = false;
	private boolean checkpoint3 = false;
	private boolean checkpoint4 = false;

	//Constructor, loads images
	public TutorialState(GameStateManager gsm)
	{
		this.gsm = gsm;
		Level2State.level2 = false;

		bg = new Background(true,"/Backgrounds/TutorialBg.png");
		heart = new HeartObject();
		heart.setPosition(heart.xPos, heart.yPos);
	}

	//Initialize default values and create stars.
	public void init() 
	{
		Level2State.level2 = false;

		checkpoint1 = true;
		checkpoint2 = false;
		checkpoint3 = false;
		checkpoint4 = false;
		spaceCount = 0;
		gsm.score = 0;
		heart.init(100);
		bg.init(heart.currentHappinessLevel);
		
		if (!stars.isEmpty())
		{
			stars.clear();
		}
		
		for(int i=0; i <numOfStars; i++)
		{
			stars.add(new StarObject(150,-i));
		}
		for(int i=0; i<stars.size(); i++)
		{
			stars.get(i).init();
		}
	}

	//Update game, checkpoint and object logic
	public void update() 
	{
		bg.update(heart.currentHappinessLevel);
		heart.update();
		
		for(int i=0; i<stars.size(); i++)
		{
			stars.get(i).update();
			if(stars.get(i).xPos < 0)
			{
				stars.remove(i);
			}
		}
		
		if (spaceCount > 5)
		{
			checkpoint1 = false;
			checkpoint2 = true;
		}
		
		if (gsm.score > 2)
		{
			checkpoint1 = false;
			checkpoint2 = false;
			checkpoint3 = true;
		}
		
		if (gsm.score > 4)
		{
			checkpoint1 = false;
			checkpoint2 = false;
			checkpoint3 = false;
			checkpoint4 = true;
		}
	}

	//Draws the game objects and text
	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		
		for (int i=0; i<stars.size(); i++)
		{
			if((stars.get(i).xPos > heart.xPos + 12 && stars.get(i).xPos < heart.xPos + 32) 
					&& (stars.get(i).yPos > heart.yPos + 12 && stars.get(i).yPos < heart.yPos + 32 ))
			{
				if(!stars.get(i).hasCollided)
				{
					gsm.score++;
					stars.get(i).hasCollided = true;
				}
			}
			
			if(stars.get(i).xPos < GamePanel.HEIGHT*GamePanel.SCALE && !stars.get(i).hasCollided)
				stars.get(i).draw(g);
		}
		
		heart.draw(g);
		g.setFont(smallFont);
		//g.drawString("Score: " + gsm.score, 5, 12);
		
		g.setColor(Color.black);
		if (checkpoint1)
		{
			g.drawString("Avoid the ground, press Space to fly", 10, 230);
		}
		if (checkpoint2)
		{
			g.drawString("Collect crystals in the center of your heart.", 10, 230);
		}
		if (checkpoint3)
		{
			g.drawString("Fill happiness to beat the level. Good luck, Sad Heart!", 10, 230);
		}
		if (checkpoint4)
		{
			g.drawString("Press E to start your journey...", 10, 230);
		}
	}
	
	//User Input
	public void keyPressed(int k) 
	{
		heart.keyPressed(k);
		if (k == KeyEvent.VK_SPACE)
			spaceCount++;
		if (k == KeyEvent.VK_E)
			gsm.setState(2);
	}

	public void keyReleased(int k) 
	{	
		heart.keyReleased(k);
		if (k == KeyEvent.VK_SPACE)
		{
			StartState.startLevel1 = false;
		}
		if (k == KeyEvent.VK_ENTER)
		{
			StartState.startTutorial = false;
		}
	}
	
	public void keyTyped(int k){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}