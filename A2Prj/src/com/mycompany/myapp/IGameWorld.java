package com.mycompany.myapp;

public interface IGameWorld {
	
	public void init();
	public void addAsteroid();
	public void addStation();
	public void addShip();
	public void incSpeed();
	public void decSpeed();
	public void turnLeft();
	public void turnRight();
	public void fire();
	public void jump();
	public void resupply();
	public void killAstrd();
	public void crash();
	public void extrmnt();
	public void clockTick();
	public void printMap();

}
