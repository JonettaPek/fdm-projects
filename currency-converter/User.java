package com.fdgroup.OOD3Assessment.CurrencyConverter;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

/**
 * <h1>public class User</h1>
 * <p>This class creates a new wallet HashMap object for each new User object created.</p>
 * @author Jonetta
 * @version 0.0.1
 */
public class User {

	private String name;
	private Map<String, Double> wallet;
	
	public User(){
		this.wallet  = new HashMap<String, Double>();
	}
	
	@JsonCreator
	public User(
			@JsonProperty("name") String name, 
			@JsonProperty("wallet") Map<String, Double> wallet) {
		
		this.name = name;
		this.wallet = wallet;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Double> getWallet() {
		return this.wallet;
	}

	public void setWallet(Map<String, Double> wallet) {
		this.wallet = wallet;
	}

	public void changeName(String name) {
		this.name = name;
	}
	
	public void addCurrency(String currency, Double amount) {
		this.wallet.put(currency, amount);
	}
	
	public Double getCurrencyAmount(String currency) {
		return this.wallet.get(currency);
	}
	
}
