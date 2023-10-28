package com.example.fdmgroup.forexplatform.dto;

import org.springframework.stereotype.Component;

@Component
public class TopupDetail {

	private String bankAccountNumber;
	private String currencyCode;
	private String amount;
	
	public TopupDetail() {
		super();
	}
	
	public TopupDetail(String bankAccountNumber, String currencyCode, String amount) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.currencyCode = currencyCode;
		this.amount = amount;
	}
	
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
