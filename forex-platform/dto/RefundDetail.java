package com.example.fdmgroup.forexplatform.dto;

import org.springframework.stereotype.Component;

@Component
public class RefundDetail {

	private String currencyCode;
	private float amount;
	
	public RefundDetail() {
		super();
	}
	
	public RefundDetail(String currencyCode, float amount) {
		super();
		this.currencyCode = currencyCode;
		this.amount = amount;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
