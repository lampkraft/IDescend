package game;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args)
	{
	    Game game = new Game();
	    game.frame.setResizable(false);
	    game.frame.add(game);
	    game.frame.pack();
	    game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game.frame.setLocationRelativeTo(null);
	    game.frame.setVisible(true);
	
	    game.start();
	}
}
