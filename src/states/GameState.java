//GameState.java
//Sad Heart
//Sarah MacDonald + Joel Kuntz

/*
 * This is the parent class for all GameStates
 */

package states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class GameState 
{
	protected GameStateManager gsm;
	
	//Initializes the current state.
	public abstract void init();

	//Updates the game variables for the current state.
	public abstract void update();

	//Creates the images for the current state.
	public abstract void draw(Graphics2D g);

	//User Input
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void keyTyped(int k);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
}