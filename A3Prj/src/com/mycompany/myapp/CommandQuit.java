package com.mycompany.myapp;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandQuit extends Command {
	private GameWorld gw;

	/**
	 * Constructor
	 * @param command, a String that will appear on the label of the command in the GUI
	 * @param world, the GameWorld that this command points to and effects
	 */
	public CommandQuit(String command, GameWorld world) {
		super(command);
		gw = world;
	}

	/**
	 * When command invoked, call method in GameWorld and prints message to console 
	 */
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Quit Pressed");
		gw.quit();
	}

}
