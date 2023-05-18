package com.example.fdmgroup.forexplatform.model;

import java.time.LocalDate;

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
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = true)
	private long orderID;
	
	@Column(name = "order_type")
	private String orderType; // market, limit, forward
	
	@Column(name = "side_type")
	private String sideType; // buy, sell
	
	@Column(name = "status")
	private String status = new String("new"); // new, fulfilled, expired
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	
	@Column(name = "currency_code")
	private String currencyCode;
	
	@Column(name = "unit_price")
	private float unitPrice;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "fk_trader_id")
	private Trader trader;

	public Order() {
		super();
		this.orderDate = LocalDate.now();
		this.expiryDate = LocalDate.now().plusDays(30);
	}

	public Order(long orderID, String orderType, String sideType, String status, String currencyCode, float unitPrice, 
			int quantity, float totalPrice, Trader trader) {
		super();
		this.orderID = orderID;
		this.orderType = orderType;
		this.sideType = sideType;
		this.status = status;
		this.currencyCode = currencyCode;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.trader = trader;
	}

	public long getOrderID() {
		return orderID;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getSideType() {
		return sideType;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public float getUnitPrice() {
		return unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setSideType(String sideType) {
		this.sideType = sideType;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

}
