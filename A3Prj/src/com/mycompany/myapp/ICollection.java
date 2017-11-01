package com.mycompany.myapp;

public interface ICollection {

	public void add(GameObject o);
	public IIterator<?>  getIterator();
	
}
