package com.mycompany.myapp;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Asteroid extends Movable {
	
	Random rand = new Random();
	
	private int size = 1 + rand.nextInt(4);
	
	public int getSize() { return size; }
	public void setSize(int size) { this.size = size; }

	/**
	 * Asteroid constructor
	 */
	public Asteroid() {
		this.setLocX(1.0 * rand.nextInt(1024));
		this.setLocY(1.0 * rand.nextInt(768));
		this.setColor(ColorUtil.GRAY);
		this.setSpeed(rand.nextInt(10));
		this.setDirection(rand.nextInt(359));
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc =  " size=" + size;
		return "Astroid: " + parentDesc + myDesc;
	}
	
}
