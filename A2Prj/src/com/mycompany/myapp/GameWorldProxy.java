package com.mycompany.myapp;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {
		
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld realWorld) {
		gw = realWorld;
	}
	
	public int getLives() {
		return gw.getLives();
	}
	
	public int getScore() {
		return gw.getScore();
	}
	
	public int getTime() {
		return gw.getTime();
	}
	
	public void init() {
		// does nothing

	}

	public void addStation() {
		// does nothing

	}

	public void addShip() {
		// does nothing

	}

	public void incSpeed() {
		// does nothing

	}

	public void decSpeed() {
		// does nothing

	}

	public void turnLeft() {
		// does nothing

	}

	public void turnRight() {
		// does nothing

	}

	public void fire() {
		// does nothing

	}

	public void jump() {
		// does nothing

	}

	public void resupply() {
		// does nothing

	}

	public void killAstrd() {
		// does nothing

	}

	public void crash() {
		// does nothing

	}

	public void extrmnt() {
		// does nothing

	}

	public void clockTick() {
		// does nothing

	}

	public void printMap() {
		// does nothing

	}

	@Override
	public void addAsteroid() {
		//does nothing
		
	}

}
