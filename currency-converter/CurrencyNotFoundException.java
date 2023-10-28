package com.fdgroup.OOD3Assessment.CurrencyConverter;

/**
 * <h1>An object from this custom exception class is thrown when transaction is unable to proceed for a particular currency</h1>
 * @author Jonetta
 * @version 0.0.1
 */
public class CurrencyNotFoundException extends Exception{

	CurrencyNotFoundException(String message) {
		super(message);
	}
}
