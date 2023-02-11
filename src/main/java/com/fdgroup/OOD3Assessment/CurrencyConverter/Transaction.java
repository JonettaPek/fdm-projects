package com.fdgroup.OOD3Assessment.CurrencyConverter;

/**
 * <h1>public class Transaction</h1>
 * <p>This class creates a new User object for each new Transaction object.
 * It is used for the sole purpose of creating Transaction objects and retrieving their fields</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class Transaction {

	/**
	 * <h2>Fields</h2>
	 */
	private User user;
	private String fromCurrency;
	private String toCurrency;
	private double amountToConvert;
	
	/**
	 * <h2>Constructors</h2>
	 */
	public Transaction() {
		user = new User();
	}
	
	public Transaction(
			User user, 
			String fromCurrency, 
			String toCurrency, 
			double amountToConvert) {
		
		this.user = user;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.amountToConvert = amountToConvert;
	}

	/**
	 * <h2>Methods</h2>
	 * <p>Getter method for User field</p>
	 * @return a User object that is performing this transaction
	 * @see User
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * <p>Setter method for User field</p>
	 * @param user a User object that is performing this transaction
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <p>Getter method for the currency to be converted from field</p>
	 * @return a String value representing the currency to be converted from
	 */
	public String getFromCurrency() {
		return this.fromCurrency;
	}

	/**
	 * <p>Setter method for the currency to be converted from field</p>
	 * @param fromCurrency a String value representing the currency to be converted from
	 */
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	/**
	 * <p>Getter method for the currency to be converted to field</p>
	 * @return a String value representing the currency to be converted to 
	 */
	public String getToCurrency() {
		return this.toCurrency;
	}

	/**
	 * <p>Setter method for the currency to be converted to field</p>
	 * @param toCurrency a String value representing the currency to be converted to
	 */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	/**
	 * <p>Getter method for the amount to be converted to</p>
	 * @return a double value representing the amount to be converted to
	 */
	public double getAmountToConvert() {
		return this.amountToConvert;
	}
	/**
	 * <p>Setter method for the amount to be converted to</p>
	 * @param amountToConvert a double value representing the amount to be converted to
	 */
	public void setAmountToConvert(double amountToConvert) {
		this.amountToConvert = amountToConvert;
	}
	
	
}
