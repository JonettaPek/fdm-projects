package com.example.fdmgroup.forexplatform.model.enums;

public enum OrderType {

	MARKET("MARKET"),
	LIMIT("LIMIT"),
	FORWARD("FORWARD");
	
	public static final OrderType[] ALL = { MARKET, LIMIT, FORWARD };
	
	private final String name;
	
	public static OrderType forName(final String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null for order type");
		}
		if (name.toUpperCase().equals("MARKET")) {
			return MARKET;
		} else if (name.toUpperCase().equals("LIMIT")) {
			return LIMIT;
		} else if (name.toUpperCase().equals("FORWARD")) {
			return FORWARD;
		}
		throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any OrderType");
	}
	
	private OrderType(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
