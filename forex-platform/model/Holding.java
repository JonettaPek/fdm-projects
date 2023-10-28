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
@Table(name = "holding")
public class Holding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holding_id", unique = true)
	private long holdingID;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "quantity")
	private long quantity;
	
	@ManyToOne
	@JoinColumn(name = "fk_portfolio_id")
	private Portfolio portfolio;

	public Holding() {
		super();
	}
	
	public Holding(long holdingID, float price, long quantity, Portfolio portfolio) {
		super();
		this.holdingID = holdingID;
		this.price = price;
		this.quantity = quantity;
		this.portfolio = portfolio;
	}

	public long getHoldingID() {
		return holdingID;
	}

	public float getPrice() {
		return price;
	}

	public long getQuantity() {
		return quantity;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setHoldingID(long holdingID) {
		this.holdingID = holdingID;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
}
