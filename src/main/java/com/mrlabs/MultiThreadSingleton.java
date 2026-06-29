package com.mrlabs;

import java.io.Serializable;

public class MultiThreadSingleton implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MultiThreadSingleton instance = null;

	private MultiThreadSingleton() {
		if(instance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance!");
		}
	}

	
	public static MultiThreadSingleton getSingleton() {
		if(instance == null) {
			synchronized (MultiThreadSingleton.class) {
				if(instance == null) {
					instance = new MultiThreadSingleton();
				}
			}
		}
	    return instance;
	}
	
	protected Object readResolveObject() {
		return instance;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException("Cloning of this class is not allowed");
	}
	
	public static void main(String[] args) {
		MultiThreadSingleton singleton1 = MultiThreadSingleton.getSingleton();
		MultiThreadSingleton singleton2 = MultiThreadSingleton.getSingleton();
		
		System.out.println(singleton1.hashCode() + ":: " +singleton2.hashCode());
	}

}
