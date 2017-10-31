package com.mycompany.myapp;

import java.util.Observable;
import com.codename1.ui.Dialog;


/**
 * @author Charles Levinsky
 *
 */
public class GameWorld extends Observable implements IGameWorld {
	
	private static double sizeX = 0, sizeY = 0; //world dimensions
	private int lives = 3, score = 0, time = 0, missiles = 0; //game statistics
	private boolean sound = false;
	private Ship ship;
	private GameObjectCollection collection;
	private GameWorldProxy proxy;


	
	public GameWorld() {
		init();
	}
	
	/**
	 *  builds default GameWorld state
	 */
	public void init() {
		lives = 3;
		score = 0;
		time = 0;
		collection = new GameObjectCollection();
		proxy = new GameWorldProxy(this);
	}
	
	public void setX(double width) {
		sizeX = width;
	}
	
	public void setY(double height) {
		sizeY = height;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTime() {
		return time;
	}
	
	public int getMissiles() {
		return missiles;
	}

	public void incTime() {
		this.time += 1;
	}

	public static double getSizex() {
		return sizeX;
	}

	public static double getSizey() {
		return sizeY;
	}
	
	public boolean getSound() {
		return sound;
	}
	

	/**
	 * creates new Asteroid 
	 * with random location, direction, size, and speed
	 */
	public void addAsteroid() {
		collection.add(new Asteroid());
	}

	/**
	 * creates new SpaceStation
	 * with unique id and random location and blink rate
	 */
	public void addStation() {
		collection.add(new SpaceStation());
	}

	/**
	 * creates new Ship
	 * with initial location at center of screen, direction and speed of 0
	 * only one Ship at allowed at a time
	 */
	public void addShip() {
		if (!(ship instanceof Ship)) {
			ship = new Ship(sizeX,sizeY);	
			collection.add(ship);
			missiles = ship.getMissile();
			updateWorld() ;
		}
		else
			System.out.println("no ship created");
	}

	/**
	 * increase Ship speed by 1
	 * max of 10
	 */
	public void incSpeed() {
		if (ship instanceof Ship)
			ship.accelerate(1);
		else 
			System.out.println("Cannot execute 'increase' – no ship has been created");
	}

	/**
	 * decrease Ship speed by 1
	 * min of 0
	 */
	public void decSpeed() {
		if (ship instanceof Ship)
			ship.accelerate(-1);
		else 
			System.out.println("Cannot execute 'decrease' – no ship has been created");
	}

	/**
	 * changes Ship direction by -10
	 * once 0 is reached, next increment will change to 350
	 */
	public void turnLeft() {
		if (ship instanceof Ship)
			ship.turn(-10); 
		else 
			System.out.println("Cannot execute 'left' – no ship has been created");
	}

	/**
	 * changes Ship direction by 10
	 * once 350 is reached, next increment will change to 0
	 */
	public void turnRight() {
		if (ship instanceof Ship)
			ship.turn(10); 
		else 
			System.out.println("Cannot execute 'right' – no ship has been created");	
	}

	/**
	 * creates a new Missile
	 * initial location = Ship location
	 */
	public void fire() {
		if (ship instanceof Ship) {
			int missileCount = ship.getMissile();
			if (missileCount > 0) {
				collection.add(new Missile(ship.getDirection(),ship.getLocX(),ship.getLocY()));
				ship.decMissiles();
				missiles = ship.getMissile();
				updateWorld() ;
			}
			else
				System.out.println("OUT OF AMMO!");	
		}
		else
			System.out.println("Cannot execute 'fire' – no ship has been created");
	}

	/**
	 * Moves Ship to starting position
	 */
	public void jump() {
		if (ship instanceof Ship) {
			ship.setLocX(sizeX / 2.0);
			ship.setLocY(sizeY / 2.0);
		}
		else
			System.out.println("Cannot execute 'jump' – no ship has been created");
	}

	/**
	 * sets Ship's missile count back to starting value (10)
	 */
	public void resupply() {
		if (ship instanceof Ship) {
			ship.reload();
			missiles = ship.getMissile();
			updateWorld() ;
		}
		else 
			System.out.println("Cannot execute 'new' – no ship has been created");	
	}

	/**
	 * Destroys an Asteroid with a Missile
	 * remove's one Asteroid and one Missile object
	 * adds 100 points to score
	 */
	public void killAstrd() {
		int asteroids = collection.getAsteroidCount(),
		       missiles = collection.getMissileCount();
		if(asteroids > 0 && missiles > 0) {
			collection.removeObject(Asteroid.class);
			collection.removeObject(Missile.class);
			score += 100;
			updateWorld() ;
		}
		else
			System.out.println("Cannot Execute 'kill' - must have a missile AND asteroid");
	}
	
	/**
	 * Ship crashes into Asteroid, both destroyed
	 * removes one Ship and one Asteroid from collection
	 * if lives remain, decrements lives
	 * otherwise game over
	 */
	public void crash() {
		int asteroids = collection.getAsteroidCount(),
		       ships = collection.getShipCount();
		if(asteroids > 0 && ships > 0) {
			collection.removeObject(Asteroid.class);
			collection.removeObject(Ship.class);
			ship = null;
			if(lives > 0)
				lives --;
			else
				System.out.println("GAME OVER!");
			updateWorld() ;
		}
		else
			System.out.println("Cannot Execute 'crash' - requires 1 ship AND 1+ asteroids" );
	}
	
	/**
	 * Two Asteroids collide, both destroyed
	 * removes 2 asteroids from collection
	 */
	public void extrmnt() {
		int asteroids = collection.getAsteroidCount();
		if(asteroids > 1) {
			collection.removeObject(Asteroid.class);
			collection.removeObject(Asteroid.class);
			updateWorld() ;
		}
		else
			System.out.println("Cannot execute 'exterminate' - not enough asteroids");
	}

	/**
	 * Increments time time clock by calling incTime()
	 * Iterates through all Game objects and updates location of all movable objects, 
	 * changes light status of Stations based on blink rate,
	 * burns missile fuel and and removes missile if out of fuel
	 * call observers to update
	 * finally calls printMap() to output world state to console
	 */
	public void clockTick() {
		incTime();
		GameObject temp;
		IIterator<?> it = collection.getIterator();
		while(it.hasNext()) {
			temp = (GameObject) it.getNext();
			if (temp instanceof IMovable) {
				((IMovable) temp).move();
			}
			if(temp instanceof SpaceStation) {
				if (time % ((SpaceStation) temp).getRate() == 0) {
					((SpaceStation) temp).toggleLights();
				}
			}
			if(temp instanceof Missile) {
				if(((Missile) temp).getFuel() > 1)
					((Missile) temp).decFuel();
				else
					it.remove();
			}
		}
		updateWorld() ;
		printMap();
	}
	
	/*
	 * creates pop-up that displays info about program and author
	 */
	public void about() {
		Dialog.show("About", "Author: Charles Levinsky \n"
				+ "CSC 133 \nVersion: 2.0", "OK", null);
	}
	
	/*
	 * if sound is on, turns it off
	 * if sound is off, turns it on
	 */
	public void toggleSound() {
		sound = !sound;
		updateWorld();
	}
	
	/*
	 * asks user whether or not they want to exit, then exits program if user agrees
	 */
	public void quit() {
		if(Dialog.show("Quit","Would you like to exit the game?", "OK", "CANCEL"))
			System.exit(0);
	}

/*
 * Iterates through entire object list and outputs current state of each object to console
 */
	public void printMap() {
		IIterator<?> it = collection.getIterator();
		while(it.hasNext()) 
			System.out.println(it.getNext());		
		System.out.println();
	}

	/*
	 *  sends updated copy of GameWorldProxy to observers and tells them to update 
	 */
	private void  updateWorld() {
		setChanged();
		proxy = new GameWorldProxy(this);
		notifyObservers(proxy);
	}

}
