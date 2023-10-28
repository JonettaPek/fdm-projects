package com.fdgroup.OOD3Assessment.CurrencyConverter;

/**
 * <h1>public class TransactionProcessor</h1>
 * <p>This class has the sole function of performing balance calculations, 
 * deducting from the from currency and adding to the to currency.</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class TransactionProcessor {

	/**
	 * <p>Subtract amountToConvert param from originalAmount param, and return the difference</p>
	 * @param originalAmount
	 * @param amountToConvert
	 * @return a double value representing the difference
	 */
	public static double deduct(double originalAmount, double amountToConvert) {
		if ( originalAmount >= amountToConvert ) {
			return originalAmount - amountToConvert;
		}
		return 0;
	}
	
	/**
	 * <p>Add amountToConvert param to originalAmount param, and return the sum</p>
	 * @param originalAmount
	 * @param amountToConvert
	 * @return a double value representing the sum
	 */
	public static double add(double originalAmount, double amountToConvert) {
		
		if ( amountToConvert > 0 ) {
			return originalAmount + amountToConvert;
		}
		return 0;
	}
	
	
	
	
	
	
	
	
}
