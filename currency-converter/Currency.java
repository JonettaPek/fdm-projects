package com.fdgroup.OOD3Assessment.CurrencyConverter;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>public class Currency</h1>
 * <p>This class represents Currency objects with fields that can be accessed 
 * by the Converter class's methods for conversion calculation purposes.
 * This class can only create objects and has no other unique methods except for getter methods.</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class Currency {

	/**
	 * <p>Fields</p>
	 */
	private String code;
	private String alphaCode;
	private String numericCode;
	private String name;
	private double rate;
	private String date;
	private double inverseRate;
	
	/**
	 * <p>Constructor</p>
	 * @param code
	 * @param alphaCode
	 * @param numericCode
	 * @param name
	 * @param rate
	 * @param date
	 * @param inverseRate
	 */
	@JsonCreator
	public Currency(
			@JsonProperty("code") String code, 
			@JsonProperty("alphaCode") String alphaCode, 
			@JsonProperty("numericCode") String numericCode, 
			@JsonProperty("name") String name, 
			@JsonProperty("rate") double rate, 
			@JsonProperty("date") String date,
			@JsonProperty("inverseRate") double inverseRate) {
		
		
		this.code = code;
		this.alphaCode = alphaCode;
		this.numericCode = numericCode;
		this.name = name;
		this.rate = rate;
		this.date = date;
		this.inverseRate = inverseRate;
	}

	/**
	 * <p>Getter method for rate field</p>
	 * @return a double value that represents the conversion conversion from USD to currency of interest (coi), that is, 1 USD = rate * coi
	 */
	public double getRate() {
		return this.rate;
	}

	/**
	 * <p>Getter method for inverseRate field</p>
	 * @return a double value that represents the conversion rate from currency of interest (coi) to USD, that is, 1 coi = inverseRate * USD
	 */
	public double getInverseRate() {
		return this.inverseRate;
	}

	
	
	
	

}


