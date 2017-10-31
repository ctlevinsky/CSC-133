package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;

/**
 * @author Charles Levinsky
 *
 */
public abstract class GameObject {

	private double locX, locY; 	//location
	private int color;
	
	public double getLocX() { return locX; }
	public double getLocY() { return locY; }
	public int getColor() { return color; }
	public void setLocX(double loc) { this.locX = loc; }
	public void setLocY(double loc) { this.locY = loc; }
	public void setColor(int color) { this.color = color; }
	
	public String toString() {
		String myDesc = "loc=" + Math.round(locX*10.0)/10.0 + "," + Math.round(locY*10.0)/10.0 + " color=[" + 
				ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]";
		return myDesc;
	}
}
