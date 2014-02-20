//GameObject.java
//Sad Heart
//Sarah MacDoanld + Joel Kuntz

/*
 * This is the parent class for all Objects in game.
 */

package objects;

import java.awt.Graphics2D;

public abstract class GameObject 
{
	public int xPos, yPos;
	public int width, height;
	public int xVel, yVel;
	
	public boolean onScreen;
	
	//Defualt Constructor
	public GameObject()
	{
		xPos = 0;
		yPos = 0;
	}
	
	//Constructor with X/Y Position set
	public GameObject(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	//Set the X and Y Position
	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	//Set the X Velocity
	public void setXVel(int x)
	{
		xVel = x;
	}
	
	//Set the Y Velocity
	public void setYVel(int y)
	{
		yVel = y;
	}
	
	//Updates the objects logic, and draws the object
	public abstract void update();
	public abstract void draw(Graphics2D g);

	//Returns respective X/Y Position/Velocity
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public int getXVel() { return xVel; }
	public int getYVel() { return yVel; }
}