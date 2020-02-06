package com.techelevator;

public class Beverages implements Vendable {

	String name;
	String alert;
	
	public Beverages(String name) {
		this.alert = "Glug Glug, Yum!";
		this.name = name;
	}
	
	@Override
	public String printDispenseAlert() {
		System.out.println(alert);
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
}
