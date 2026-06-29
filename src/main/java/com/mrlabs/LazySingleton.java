package com.mrlabs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LazySingleton implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private static LazySingleton instance = null;
	
	private LazySingleton() {
		if (instance != null) {
	        throw new RuntimeException("Use getInstance() method to get the single instance!");
	    }
	}
	
	public static LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
	
	//fix deserialise break singleton
	protected Object readResolve() {
        return getInstance(); // Returns the original instance during deserialization
    }
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException("Cloning of this class is not allowed");
	}
	
	
	
	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException {
		LazySingleton instance = LazySingleton.getInstance();
		
		System.out.println(" instance : "+instance.hashCode());
		
		
		// Step 2: Use reflection to grab the private constructor [00:36:37]
        Constructor<LazySingleton> constructor = LazySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true); // Forces the private constructor to be accessible.

        // Step 3: Create a rogue second instance
       // LazySingleton instance2 = constructor.newInstance();
        
       // System.out.println(" instance2 : "+instance2.hashCode());
        
        
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.obj"));
        LazySingleton lazySingleton = LazySingleton.getInstance();
        outputStream.writeObject(lazySingleton);
        outputStream.close();
        
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.obj"));
        LazySingleton deserializeObj = (LazySingleton)inputStream.readObject();
        inputStream.close();
        
        System.out.println("deserialize "+deserializeObj.hashCode());
        
        //System.out.println(instance.clone().hashCode());
        
	}

}
