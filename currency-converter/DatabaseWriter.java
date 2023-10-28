package com.fdgroup.OOD3Assessment.CurrencyConverter;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <h1>public class DatabaseWriter</h1>
 * <p>This class has the sole function of serialising User objects to updated-users.json file</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class DatabaseWriter {
	
	/**
	 *  <p>Serialise User objects and save their states in a json file format</p>
	 * @param updatedUsers an array of User objects with updated states
	 * @see User
	 */
	public static void serialiseUserObjects(User[] updatedUsers) {
		
		ObjectMapper map = new ObjectMapper();
		try {
			map.writeValue(new File(".\\src\\main\\resources\\updated-users.json"), updatedUsers);
		} catch (IOException ioe) {
			
		}
	}
	
}
