package com.example.fdmgroup.forexplatform.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "balance")
public class Balance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balance_id", unique = true)
	private long balanceID;
	
	@Column(name = "currency_code")
	private String currencyCode;
	
	@Column(name = "amount")
	private float amount;
	
	@ManyToOne
	@JoinColumn(name = "fk_wallet_id")
	private Wallet wallet;

	public Balance() {
		super();
	}

	public Balance(long balanceID, String currencyCode, float amount, Wallet wallet) {
		super();
		this.balanceID = balanceID;
		this.currencyCode = currencyCode;
		this.amount = amount;
		this.wallet = wallet;
	}

	public long getBalanceID() {
		return balanceID;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public float getAmount() {
		return amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setBalanceID(long balanceID) {
		this.balanceID = balanceID;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public void increase(float amount) {
		this.amount += amount;
	}

	public void decrease(float amount) {
		this.amount -= amount;
	}
}