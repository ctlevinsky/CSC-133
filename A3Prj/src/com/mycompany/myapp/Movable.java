package com.mycompany.myapp;
import java.util.Random;

public abstract class Movable extends GameObject implements IMovable {

	private Random rand = new Random();
	
	private int speed = rand.nextInt(10);
	private double direction = 1.0 * rand.nextInt(359);
	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	/**
	 * updates location of objects
	 * based on current location, direction , and speed
	 */
	public void move() {
		double theta = ((90 - this.getDirection()) / 180 ) * Math.PI;
		double deltaX = Math.cos(theta) * speed;
		double deltaY = Math.sin(theta) * speed;
		this.setLocX(this.getLocX() + deltaX);
		this.setLocY(this.getLocY() + deltaY);
	}
	
	public String toString(){
		String parentDesc = super.toString();
		String myDesc = " speed=" + speed + " dir=" + Math.round(direction*10.0)/10.0;
		return parentDesc + myDesc;
	}

}
