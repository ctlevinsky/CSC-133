package com.mycompany.myapp;
import java.util.ArrayList;

/**
 * @author Charles Levinsky
 *
 */
public class GameWorld {

	private final static double sizeX = 1024.0, sizeY = 768.0; //world dimensions
	private int lives = 3, score = 0, time = 0; //game statistics
	private Ship ship; 
	private ArrayList<GameObject> worldArray = new ArrayList<GameObject>(); //stores all game objects created
	
	public double getSizeX() { return sizeX; }
	public double getSizeY() { return sizeY; }
	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }
	
	/**
	 *  builds default GameWorld state
	 */
	public void init(){
		ship = new Ship(sizeX,sizeY);
		worldArray.add(ship);
	}

	/**
	 * 'a' - creates new Asteroid 
	 * with random location, direction, size, and speed
	 */
	public void addAstroid() {
		worldArray.add(new Asteroid());
	}

	/**
	 * 'b' - creates new SpaceStation
	 * with unique id and random location and blink rate
	 */
	public void addStation() {
		worldArray.add(new SpaceStation());
	}
	
	/**
	 * 's' - creates new Ship
	 * with initial location at center of screen, direction and speed of 0
	 * only one Ship at allowed at a time
	 */
	public void addShip() {
		if (!(ship instanceof Ship)) {
			ship = new Ship(sizeX,sizeY);	
			worldArray.add(ship);
		}
		else
			System.out.println("no ship created");
	}
	
	/**
	 * 'i' - increase Ship speed by 1
	 * max of 10
	 */
	public void incSpeed() { 
		if (ship instanceof Ship)
			ship.accelerate(1);
		else 
			System.out.println("Cannot execute 'increase' � no ship has been created");
	}
	
	/**
	 * 'd' - decrease Ship speed by 1
	 * min of 0
	 */
	public void decSpeed() { 
		if (ship instanceof Ship)
			ship.accelerate(-1);
		else 
			System.out.println("Cannot execute 'decrease' � no ship has been created");
	}
	
	/**
	 * 'l' - changes Ship direction by -10
	 * once 0 is reached, next increment will change to 350
	 */
	public void turnLeft() { 
		if (ship instanceof Ship)
			ship.turn(-1); 
		else 
			System.out.println("Cannot execute 'left' � no ship has been created");
	}
	
	/**
	 * 'r' - changes Ship direction by 10
	 * once 350 is reached, next increment will change to 0
	 */
	public void turnRight() { 
		if (ship instanceof Ship)
			ship.turn(1); 
		else 
			System.out.println("Cannot execute 'right' � no ship has been created");	
	}
	
	/**
	 * 'f' - creates a new Missile
	 * initial location = Ship location
	 */
	public void fire() { 
		if (ship instanceof Ship) {
			int missileCount = ship.getMissile();
			if (missileCount > 0) {
				worldArray.add(new Missile(ship.getDir(),ship.getLocX(),ship.getLocY()));
				ship.setMissile(missileCount - 1);
			}
			else
				System.out.println("OUT OF AMMO!");	
		}
		else
			System.out.println("Cannot execute 'fire' � no ship has been created");
	}
	
	/**
	 * 'j' - moves Ship to starting position
	 */
	public void jump() {
		if (ship instanceof Ship) {
			ship.setLocX(sizeX / 2.0);
			ship.setLocY(sizeY / 2.0);
		}
		else
			System.out.println("Cannot execute 'jump' � no ship has been created");
	}

	/**
	 * 'n' - sets Ship's missile count back to starting value (10)
	 */
	public void resupply() { 
		if (ship instanceof Ship)
			ship.setMissile(10); 
		else 
			System.out.println("Cannot execute 'new' � no ship has been created");	
	}
	
	/**
	 * 'k' - destroys an Asteroid with a Missile
	 * remove's one Asteroid and one Missile object
	 * adds 100 points to score
	 */
	public void killAstrd() {
		int sz = worldArray.size();
		boolean foundMsl = false, foundAstrd = false;
		int mslIndex = 0, astrdIndex = 0;
		for(int i = 0; i < sz; i++) {
			if(worldArray.get(i) instanceof Missile) {
				foundMsl = true;
				mslIndex = i;
			}
			else if (worldArray.get(i) instanceof Asteroid) {
				foundAstrd = true;
				astrdIndex = i;
			}
			if (foundMsl == true && foundAstrd ==  true)
				break;
		}
		if(foundMsl == true && foundAstrd == true) {
			score += 100;
			if (mslIndex < astrdIndex) { 		//remove higher index first to avoid errors from shifting array
				worldArray.remove(astrdIndex);
				worldArray.remove(mslIndex);	
			}
			else {
				worldArray.remove(mslIndex);
				worldArray.remove(astrdIndex);
			}
		}
		else
			System.out.println("Cannot Execute 'kill' - must have a missile AND asteroid");
	}
	
	/**
	 * 'c' - Ship crashes into Asteroid, both destroyed
	 * removes one Ship and one Asteroid from GameWorld
	 * if lives remain, decrements lives
	 * otherwise game over
	 */
	public void crash() {
		if (ship instanceof Ship ) {
			boolean foundShip = false, foundAstrd = false;
			int shpIndex = 0, astrdIndex = 0;
			int sz = worldArray.size();
			for (int i = 0; i < sz; i++) {
				if(worldArray.get(i) instanceof Ship) {
					foundShip = true;
					shpIndex = i;
				}
				else if (worldArray.get(i) instanceof Asteroid) {
					foundAstrd = true;
					astrdIndex = i;
				}
				if (foundShip == true && foundAstrd ==  true)
					break;
			}
			if(foundShip == true && foundAstrd == true) {
				if (shpIndex < astrdIndex) { //remove higher index first to preserve array
					worldArray.remove(astrdIndex);
					worldArray.remove(shpIndex);	
				}
				else {
					worldArray.remove(shpIndex);
					worldArray.remove(astrdIndex);
				}
				ship = null;
				if(lives > 0) 
					lives--;
				else
					System.out.println("GAME OVER!");
			}
			else
				System.out.println("Cannot Execute 'crash' - no aseroid found");
		}
		else
			System.out.println("Cannot execute 'crash' � no ship has been created");
	}
	
	/**
	 * 'x' - two Asteroids collide, both destroyed
	 * removes 2 asteroids from GameWorld
	 */
	public void extrmnt() {
		int sz = worldArray.size();
		int a1 = 0, a2 = 0;
		int counter = 0;
		for(int i = 0; i < sz; i++) {
			if(worldArray.get(i) instanceof Asteroid) {
				counter++;
				if(counter == 1) 
					a1 = i;
				else if(counter == 2) {
					a2 = i;
					break;
				}
				else break;
			}
		}
		if(counter >= 2) {
			worldArray.remove(a2);
			worldArray.remove(a1);
		}
		else
			System.out.println("Connot execute 'exterminate' - not enough asteroids");
	}
	
	/**
	 * 't' - increment time by one, update GameObjects
	 * update location of all Movable objects
	 * decrement fuel on all Missiles
	 * toggle lights on SpaceStations (based on rate)
	 */
	public void clockTick() {
		int sz = worldArray.size();
		time++;
		if(sz > 0) {
			for( int i = sz-1; i >= 0; i-- ) {
				if (worldArray.get(i) instanceof IMovable) {
					IMovable mObj = (IMovable)worldArray.get(i);
					mObj.move();
				}
				if (worldArray.get(i) instanceof SpaceStation) {
					SpaceStation ssObj = (SpaceStation)worldArray.get(i);
					if(time % ssObj.getRate() == 0) {
						if (ssObj.isLightOn())
							ssObj.setLightOn(false);	
						else
							ssObj.setLightOn(true);
					}
				}
				if (worldArray.get(i) instanceof Missile) {
					Missile mslObj = (Missile)worldArray.get(i);
					int fuelLvl = mslObj.getFuel();
					if( fuelLvl > 1)
						mslObj.setFuel(fuelLvl - 1);
					else {
						mslObj = null;
						worldArray.remove(i);
					}
				}
			}
		}	
	}

	/**
	 * 'p' - prints GameWorld stats to console
	 * score, missiles remaining, and game time
	 */
	public void print() {
		System.out.println("Score=" + score + " Missiles=" + ship.getMissile() + " Time:"+ time);
	}
	
	/**
	 * 'm' - prints state of all GameObjects
	 * location, speed, direction, color, etc of all concrete objects
	 */
	public void printMap() {
		int sz = worldArray.size();
		for(int i = 0; i < sz; i++)
			System.out.println(worldArray.get(i));
		System.out.println();
	}
}
	