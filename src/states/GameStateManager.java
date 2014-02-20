//GameStateManager.java
//Sad Heart
//Sarah MacDonald + Joel Kuntz

/*
 * Manages the currently active GameState.
 */

package states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager 
{
	//Stores all of the possible GameStates
	public ArrayList<GameState> states;
	
	//The GameStates
	public static final int STARTSTATE = 0;
	public static final int TUTORIALSTATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int LEVEL2STATE = 3;
	
	public int score; 
	public int topScore = 0;
	
	private int currentState;
	
	//Constructor. Adds all the GameStates to the ArrayList. And loads the default level
	public GameStateManager()
	{
		states = new ArrayList<GameState>();
		states.add(new StartState(this));
		states.add(new TutorialState(this));
		states.add(new Level1State(this));
		states.add(new Level2State(this));

		setState(STARTSTATE);
	}

	//Calls the currentState's update function.
	public void update()
	{
		states.get(currentState).update();
	}
	
	//Calls the currentState's draw function.
	public void draw(Graphics2D g)
	{
		states.get(currentState).draw(g);
	}
	
	//Sets the currentState and initializes it.
	public void setState(int state)
	{
		currentState = state;
		states.get(state).init();
	}
	
	//Returns the currentState.
	public int getCurrentState()
	{
		return currentState;
	}

	//Passes user input to the currentState
	public void keyPressed(int k)
	{		
		states.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k)
	{
		states.get(currentState).keyReleased(k);
	}
	
	public void keyTyped(int k)
	{
		states.get(currentState).keyTyped(k);
	}
	
	public void mousePressed(MouseEvent e)
	{
		states.get(currentState).mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		states.get(currentState).mouseReleased(e);
	}
}