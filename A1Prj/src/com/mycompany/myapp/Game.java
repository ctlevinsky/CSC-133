package com.mycompany.myapp;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener; 
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;


public class Game extends Form {

	private GameWorld gw; 
	private boolean quitFlag = false;
	
	/**
	 * Game constructor
	 * creates new GameWorld and initializes it
	 * runs play method
	 */
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	/**
	 * User Interface into Game
	 * allows players to enter commands which will run methods in GameWorld
	 */
	private void play() {
		Label myLabel=new Label ("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(quitFlag) {						
					switch(sCommand.charAt(0)){
						case 'y' : System.exit(0); break;
						default: quitFlag = false; break;
					}
				}
				else {
					switch (sCommand.charAt(0)){
					case 'a':	gw.addAstroid();break;
					case 'b':	gw.addStation();break;
					case 's':	gw.addShip();	break;
					case 'i':	gw.incSpeed();	break;
					case 'd':	gw.decSpeed();	break;
					case 'l':	gw.turnLeft();	break;
					case 'r':	gw.turnRight(); break;
					case 'f':	gw.fire();		break;	
					case 'j':	gw.jump();		break;
					case 'n':	gw.resupply();	break;
					case 'k':	gw.killAstrd();	break;
					case 'c':	gw.crash();		break;
					case 'x':	gw.extrmnt();	break;
					case 't':	gw.clockTick();	break;
					case 'p':	gw.print();		break;
					case 'm':	gw.printMap(); 	break;
					case 'q':	quitFlag = true;
								System.out.println("Would you like to quit? 'Y' or 'N'");
								break;
					default :	System.out.println("Invalid Input");
					}
				}
			}
		});
	}
	
}
