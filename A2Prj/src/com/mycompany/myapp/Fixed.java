package com.mycompany.myapp;

public abstract class Fixed extends GameObject {

	private int id;
	private static int staticId = 0; //unique identification number

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getStaticId() {
		return staticId;
	}

	public static void setStaticId(int staticId) {
		Fixed.staticId = staticId;
	}

	public Fixed() {
		this.id = staticId;
		staticId++;
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " id=" + id;
		return parentDesc + myDesc;
	}
}
