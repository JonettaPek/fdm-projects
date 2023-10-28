package com.example.fdmgroup.forexplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "wallet")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_id", unique = true)
	private long walletID;
	
	@Column(name = "total_value")
	private float totalValue; // USD
	
	@OneToOne(mappedBy = "wallet")
	@JoinColumn(name = "fk_trader_id")
	private Trader trader;

	@OneToMany(mappedBy = "wallet")
	private List<Balance> balancies = new ArrayList<>();
	
	public Wallet() {
		super();
	}

	public Wallet(long walletID, float totalValue, Trader trader) {
		super();
		this.walletID = walletID;
		this.totalValue = totalValue;
		this.trader = trader;
	}

	public long getWalletID() {
		return walletID;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public Trader getTrader() {
		return trader;
	}

	public List<Balance> getBalancies() {
		return balancies;
	}

	public void setWalletID(long walletID) {
		this.walletID = walletID;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	public void setBalancies(List<Balance> balancies) {
		this.balancies = balancies;
	}
	
	public void addBalance(Balance balance) {
		this.balancies.add(balance);
	}
	
//	public void replaceBalance(int index, Balance balance) {
//		this.balancies.set(index, balance);
//	}
	
	public void deposit(float amount) {
		this.totalValue += amount;
	}
	
	public void withdraw(float amount) {
		this.totalValue -= amount;
	}
}