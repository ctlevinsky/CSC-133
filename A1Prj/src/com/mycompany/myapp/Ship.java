package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;

public class Ship extends Movable implements ISteerable{

	private int missileCount = 10;
	
	/**
	 * Ship constructor
	 * @param locX x-dimension of game screen (used to calculate center of screen)
	 * @param locY y-dimension of game screen (used to calculate center of screen)
	 */
	public Ship(double locX, double locY) {
		this.setSpeed(0);
		this.setDir(0);
		this.setColor(ColorUtil.BLUE);
		this.setLocX(locX / 2.0);
		this.setLocY(locY / 2.0);
	}
	
	public int getMissile() {
		return this.missileCount;
	}
	
	public void setMissile(int missiles) {
		missileCount = missiles;
	}

	/* changes Ship's direction 
	 * @param dir either positive or negative to determine right or left respectively
	 * @see com.mycompany.myapp.ISteerable#turn(int)
	 */
	public void turn(int dir) {
		
		double currentDir = this.getDir();
		
		if (currentDir < 350 && dir >= 0)  //turn right
			this.setDir(currentDir + 10);
		else if (currentDir >= 350 && dir >= 0) //completed 360 right turn
			this.setDir(0);
		else if (currentDir >= 10 &&  dir < 0) //left turn
			this.setDir(currentDir - 10);
		else
			this.setDir(350); //turn left from 0
	}
	
	/* changes Ship's speed
	 * @param change either positive or negative to spped up or slow down respectively
	 * @see com.mycompany.myapp.ISteerable#accelerate(int)
	 */
	public void accelerate(int change) {
		int currentSpeed = this.getSpeed();
		if (change >= 0 && currentSpeed < 10) { //speed up if speed < max
			this.setSpeed(currentSpeed + 1);
		}
		else if (currentSpeed > 0)				//slow down if speed > min
			this.setSpeed(currentSpeed - 1);
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " missiles=" + missileCount;
		return "Ship: " + parentDesc + myDesc;
	}
	
}
