package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	private double locX, locY; 	//location
	private int color;

	public double getLocX() {
		return locX;
	}

	public void setLocX(double locX) {
		this.locX = locX;
	}

	public double getLocY() {
		return locY;
	}

	public void setLocY(double locY) {
		this.locY = locY;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "GameObject locX=" + Math.round(locX*10.0)/10.0 + 
			   ", locY=" + Math.round(locY*10.0)/10.0 + 
			   " color=[" + ColorUtil.red(color) + "," + 
			   ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]";
	}

	
}
