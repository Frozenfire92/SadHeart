//Level2State.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This class is the level2 State.
 */

package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.GamePanel;
import objects.Background;
import objects.GameOver;
import objects.HappinessBarObject;
import objects.HeartObject;
import objects.StarObject;

public class Level2State extends GameState
{
	//Font
	public Font smallFont = new Font("Verdana",Font.PLAIN, 12);
 
	public static boolean level2 = true;

	//Game Objects
	public HeartObject heart;
	public Background bg;
	public HappinessBarObject hb;
	public ArrayList<StarObject> stars = new ArrayList<StarObject>();
	public int numberOfStars;
	public GameOver go;
	
	public boolean gameOver = false;
	private Rectangle menuButton;

	//Constructor initializes objects and image.
	public Level2State(GameStateManager gsm)
	{
		this.gsm = gsm;
		bg = new Background("/Backgrounds/Lvl2BgSheet.png");
		heart = new HeartObject();
		hb = new HappinessBarObject();
		go = new GameOver();
		menuButton = new Rectangle(115, 115, 75, 45);
		gameOver = false;
	}
	
	//Initializes the gameStates objects and values to default. Creates the stars
	public void init() 
	{
		gameOver = false;

		heart.init(30);
		heart.setPosition(heart.xPos, heart.yPos);
		hb.init(heart.currentHappinessLevel);
		bg.init(heart.currentHappinessLevel);
		bg.setVel(-1);
		stars.clear();
		
		int numberOfStars = 300;
		for(int i = 0; i < numberOfStars; i++)
		{
			stars.add(new StarObject(1000,-i));			
		}
		
		for(int i = 0; i <stars.size();i++)
		{
			stars.get(i).init();
			stars.get(i).setxVel(-5);
		}	
	}

	//Updates the level and object logic
	public void update() 
	{
		heart.update();
		bg.update(heart.currentHappinessLevel);
		hb.update(heart.currentHappinessLevel);
		
		for(int i = 0; i <stars.size();i++)
		{
			stars.get(i).update();
			//stars.get(i).setxVel(gsm.score);
			if(stars.get(i).xPos < 0)
			{
				if(!stars.get(i).hasCollided)
				{
					heart.currentHappinessLevel -= 1;
					if (heart.currentHappinessLevel < 1) heart.currentHappinessLevel = 0;
				}
				stars.remove(i);
			}
		}
		
		if (heart.currentHappinessLevel <= 0) gameOver = true;
		if (heart.yPos > GamePanel.HEIGHT-59) gameOver = true;
		if (gameOver && gsm.score > gsm.topScore) gsm.topScore = gsm.score;
	
		if(gsm.score >= 1) 	bg.setVel(-2);
		if(gsm.score >= 10) bg.setVel(-3);
		if(gsm.score >= 15) bg.setVel(-4);
		if(gsm.score >= 20) bg.setVel(-5);
		if(gsm.score >= 25) bg.setVel(-6);
		if(gsm.score >= 30) bg.setVel(-9);
		if(gsm.score >= 35) bg.setVel(-10);
		if(gsm.score >= 40) bg.setVel(-12);
		if(gsm.score >= 45) bg.setVel(-14);
		if(gsm.score >= 50) bg.setVel(-16);
		if(gsm.score >= 55) bg.setVel(-18);
		if(gsm.score >= 60) bg.setVel(-20);
		if(gsm.score >= 65) bg.setVel(-22);
		if(gsm.score >= 70) bg.setVel(-24);
		if(gsm.score >= 80) bg.setVel(-26);
		if(gsm.score >= 90) bg.setVel(-28);
		if(gsm.score >= 100) bg.setVel(-30);
					
		if (gameOver) bg.setVel(-1);
	}

	//Draws the objects and text on screen
	public void draw(Graphics2D g) 
	{
		g.setFont(smallFont);
		g.setColor(Color.black);

		bg.draw(g);
		hb.draw(g);
		
		if(!gameOver)
		{
			for(int i = 0; i < stars.size();i++)
			{
				if((stars.get(i).xPos > heart.xPos + 12 && stars.get(i).xPos < heart.xPos + 32) 
						&& (stars.get(i).yPos > heart.yPos + 12 && stars.get(i).yPos < heart.yPos + 32 ))
				{
					if(!stars.get(i).hasCollided)
					{
						gsm.score+= 1;
						heart.currentHappinessLevel += 20;
						stars.get(i).hasCollided = true;
					}
				}
				if(stars.get(i).xPos < GamePanel.HEIGHT*GamePanel.SCALE && !stars.get(i).hasCollided)
					stars.get(i).draw(g);
			}
			heart.draw(g);
			g.drawString("Score: " + gsm.score, 5, 12);
		}
		else if (gameOver)
		{
			go.showGameOver(g);
			g.drawString("" + gsm.score, 265, 130);
			g.drawString("" + gsm.topScore, 265, 165);
		}
	}

	//User Input
	public void keyPressed(int k) 
	{
		heart.keyPressed(k);
		if(k == KeyEvent.VK_ENTER && gameOver) gsm.setState(0);
	}

	public void keyReleased(int k) 
	{
		heart.keyReleased(k);
		if (k == KeyEvent.VK_SPACE) StartState.startLevel1 = false;
		if (k == KeyEvent.VK_ENTER) StartState.startTutorial = false;
	}

	public void keyTyped(int k){}
	public void mousePressed(MouseEvent e) 
	{
		if (menuButton.contains(GamePanel.mouseX, GamePanel.mouseY) && gameOver)
			gsm.setState(0);
	}
	public void mouseReleased(MouseEvent e) {}
}