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
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_id", unique = true)
	private long portfolioID;
	
	@Column(name = "total_value")
	private float totalValue;
	
	@OneToOne(mappedBy = "portfolio")
	@JoinColumn(name = "fk_trader_id")
	private Trader trader;
	
	@OneToMany(mappedBy = "portfolio")
	private List<Holding> holdings = new ArrayList<>();

	public Portfolio() {
		super();
	}

	public Portfolio(long portfolioID, float totalValue, Trader trader) {
		super();
		this.portfolioID = portfolioID;
		this.totalValue = totalValue;
		this.trader = trader;
	}

	public long getPortfolioID() {
		return portfolioID;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public Trader getTrader() {
		return trader;
	}

	public List<Holding> getHoldings() {
		return holdings;
	}

	public void setPortfolioID(long portfolioID) {
		this.portfolioID = portfolioID;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}
	
	public void addHolding(Holding holding) {
		this.holdings.add(holding);
	}
}
