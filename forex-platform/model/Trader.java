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
@Table(name = "trader")
public class Trader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trader_id", unique = true)
	private long traderID;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "fk_portfolio_id")
	private Portfolio portfolio;
	
	@OneToOne
	@JoinColumn(name = "fk_wallet_id")
	private Wallet wallet;
	
	@OneToMany(mappedBy = "trader")
	private List<Order> orders = new ArrayList<>();

	public Trader() {
		super();
	}

	public Trader(long traderID, String firstName, String lastName, String email, String password, Portfolio portfolio,
			Wallet wallet) {
		super();
		this.traderID =traderID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.portfolio = portfolio;
		this.wallet = wallet;
	}

	public long getTraderID() {
		return traderID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setTraderID(long traderID) {
		this.traderID = traderID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
}
