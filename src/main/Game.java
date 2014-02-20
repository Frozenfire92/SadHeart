//Game.java
//Sad Heart
//Sarah MacDonald + Joel Kuntz

/*
 * The main window that will display the GamePanel.
 * It sets the title of the window, the operation for when
 * the close button on the window is pressed,
 * and initializes a new GamePanel and makes itself visible.
 */

package main;

import javax.swing.JFrame;

public class Game extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Game()
	{
		setTitle("Sad Heart");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(new GamePanel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		Game game = new Game();	
	}
}