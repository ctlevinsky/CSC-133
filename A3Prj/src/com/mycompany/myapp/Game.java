package com.mycompany.myapp;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;



public class Game extends Form {
	
	
	private GameWorld gw;
	private MapView mv; // new in A2
	private PointsView pv; // new in A2
	private Toolbar myToolbar;


	public Game() {
		//Create instances of Observable and Observer objects and link themGame
		gw = new GameWorld(); // create “Observable”
		mv = new MapView(); // create an “Observer” for the map
		pv = new PointsView(gw); // create an “Observer” for the points
		gw.addObserver(mv); // register the map Observer
		gw.addObserver(pv); // register the points observer

		
		//define layout and Tool bar
		setToolbar(myToolbar = new Toolbar());		
		this.setLayout(new BorderLayout());
		
		
		//Create, set, and style Points View at top of screen 
		Container pointsContainer = new Container();
		add(BorderLayout.NORTH,pointsContainer);
		pointsContainer.getAllStyles().setPadding(Component.LEFT, 150);
		pointsContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		pointsContainer.add(pv);
		
		
		//Create and set Map View
		Container mapContainer = new Container();
		add(BorderLayout.CENTER,mapContainer);
		mapContainer.add(mv);
		mapContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.LTGRAY));

		
		//Create instances of command objects to be used
		Command newCommand = new Command("New"); 		//does nothing yet
		Command saveCommand = new Command("Save");		//does nothing yet
		Command undoCommand = new Command("Undo");	//does nothing yet
		CommandAbout aboutCommand = new CommandAbout("About", gw);
		CommandQuit quitCommand = new CommandQuit("Quit", gw);
		CommandSound toggleSound = new CommandSound("Sound", gw);
		CommandAddAsteroid addAsteroid = new CommandAddAsteroid("Add Asteroid", gw);
		CommandAddStation addStation = new CommandAddStation("Add Station", gw);
		CommandAddShip addShip = new CommandAddShip("Add Ship", gw);
		CommandIncrease inc = new CommandIncrease("Increase", gw);
		CommandDecrease dec = new CommandDecrease("Decrease", gw);
		CommandLeft left = new CommandLeft("Left", gw);
		CommandRight right= new CommandRight("Right", gw);
		CommandFire fire = new CommandFire("Fire", gw);
		CommandJump jump = new CommandJump("Jump", gw);
		CommandLoadMissiles resupply = new CommandLoadMissiles("Load Missiles", gw);
		CommandKillAsteroid kill = new CommandKillAsteroid("Kill Asteroid", gw);
		CommandCrashShip cs = new CommandCrashShip("Crash Ship", gw);
		CommandExterminate ex = new CommandExterminate("Exterminate", gw);
		CommandTick tick = new CommandTick("Tick", gw);
		
		
		//Create Check box and add sound toggle
		CheckBox soundBox = new CheckBox("Toggle Sound");
		soundBox.getAllStyles().setBgTransparency(255);
		soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundBox.setCommand(toggleSound);
		toggleSound.putClientProperty("SideComponent", soundBox); 
		

		//Add Commands to Overflow Menu
		myToolbar.addCommandToOverflowMenu(toggleSound);
		myToolbar.addCommandToOverflowMenu(newCommand);
		myToolbar.addCommandToOverflowMenu(saveCommand);
		myToolbar.addCommandToOverflowMenu(undoCommand);
		myToolbar.addCommandToOverflowMenu(aboutCommand);
		myToolbar.addCommandToOverflowMenu(quitCommand);
		
		
		//Add Commands to Side bar
		myToolbar.addCommandToSideMenu(toggleSound);
		myToolbar.addComponentToSideMenu(new SideBarButton(addAsteroid));
		myToolbar.addComponentToSideMenu(new SideBarButton(addStation));
		myToolbar.addComponentToSideMenu(new SideBarButton(addShip));
		myToolbar.addComponentToSideMenu(new SideBarButton(resupply));
		myToolbar.addComponentToSideMenu(new SideBarButton(kill));
		myToolbar.addComponentToSideMenu(new SideBarButton(cs));
		myToolbar.addComponentToSideMenu(new SideBarButton(ex));
		myToolbar.addComponentToSideMenu(new SideBarButton(tick));
	

		//Add Key Listeners
		addKeyListener('r', right);
		addKeyListener('l', left);
		addKeyListener('j', jump);
		addKeyListener('i', inc);
		addKeyListener('d', dec);
		addKeyListener('f', fire);
		
		//display everything created on screen
		this.show();
		
		//set world size based on size of window created
		gw.setX(mapContainer.getWidth() * 1.0);
		gw.setY(mapContainer.getHeight() * 1.0);
	}
	
		
}


