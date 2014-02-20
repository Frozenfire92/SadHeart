//StartState.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is the games main menu state.
 */

package states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.GamePanel;
import objects.Background;
import objects.StarObject;

public class StartState extends GameState
{
	//Font
	public Font smallFont = new Font("Verdana",Font.PLAIN, 12);
	
	//Switches for starting level/tutorial
	public static boolean startLevel1 = false;
	public static boolean startTutorial = false;
	
	//Game Objects
	public Background bg;
	public ArrayList<StarObject> stars = new ArrayList<StarObject>();
	private int numOfStars = 100;
	
	//Menu Buttons
	private Rectangle playButton, tutorialButton; 
	
	//Constructor for the state. Loads the background
	public StartState(GameStateManager gsm)
	{
		this.gsm = gsm;
		bg = new Background(true,"/Backgrounds/StartBg.png");
	}

	//Initializes the state to default values. Adds Stars. Sets menu button positions.
	public void init() 
	{
		bg.init(100);
		
		if (!stars.isEmpty())
		{
			stars.clear();
		}
		
		for(int i=0; i <numOfStars; i++)
		{
			stars.add(new StarObject(180,-i));
		}
		for(int i=0; i<stars.size(); i++)
		{
			stars.get(i).init();
		}
		
		playButton = new Rectangle(0,200,200,50);
		tutorialButton = new Rectangle(200,200,200,50);
	}

	//Updates stars and goes to level/tutorial if toggled
	public void update() 
	{
		for(int i=0; i<stars.size(); i++)
		{
			stars.get(i).update();
			if(stars.get(i).xPos < 0)
			{
				stars.remove(i);
			}
		}
		
		if(startLevel1) gsm.setState(2);
		if(startTutorial) gsm.setState(1);
	}

	//Draws the background and stars.
	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		for (int i=0; i<stars.size(); i++)
		{
			if(stars.get(i).xPos < GamePanel.HEIGHT*GamePanel.SCALE)
				stars.get(i).draw(g);
		}
		
		//For testing where the menu button collision area is
		//g.draw(playButton);
		//g.draw(tutorialButton);
	}
	
	//User Input
	public void keyPressed(int k) 
	{
		if (k == KeyEvent.VK_SPACE)
		{
			startLevel1 = true;
		}
		if (k == KeyEvent.VK_ENTER)
		{
			startTutorial = true;
		}
	}

	public void keyReleased(int k) 
	{
		if (k == KeyEvent.VK_SPACE) StartState.startLevel1 = false;
		if (k == KeyEvent.VK_ENTER) StartState.startTutorial = false;
	}
	
	public void keyTyped(int k){}
	
	public void mousePressed(MouseEvent e)
	{
		if (playButton.contains(GamePanel.mouseX, GamePanel.mouseY))
			startLevel1 = true;
		if (tutorialButton.contains(GamePanel.mouseX, GamePanel.mouseY))
			startTutorial = true;
	}
	public void mouseReleased(MouseEvent e){}
}