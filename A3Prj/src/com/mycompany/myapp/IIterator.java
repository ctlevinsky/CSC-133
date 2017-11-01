package com.mycompany.myapp;

public interface IIterator<E> {

	public E getNext();
	public boolean hasNext();
	public  void remove();
	
}
