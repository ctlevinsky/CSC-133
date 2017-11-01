package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;

public class Missile extends Movable {

	private int fuel = 10;
	
	/**
	 * Missile constructor
	 * @param d current direction of Ship that created Missiles
	 * @param locX current x-coordinate of Ship that created Missiles
	 * @param locY current y-coordinate of Ship that created Missiles
	 */
	public Missile(double d, double locX, double locY) {
		this.setSpeed(20);
		this.setDirection(d);
		this.setColor(ColorUtil.MAGENTA);
		this.setLocX(locX);
		this.setLocY(locY);		
	}
	
	public int getFuel() { 
		return fuel; 
	}
		
	public void decFuel() { 
		fuel--; 
	}

	public String toString() {
		String parentDesc = super.toString();
		String myDesc =  " fuel=" + fuel;
		return "Missile: " + parentDesc + myDesc;
	}
	

}


