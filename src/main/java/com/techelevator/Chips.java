package com.techelevator;

public class Chips implements Vendable {

	String name;
	String alert;
	
	public Chips(String name) {
		this.alert = "Crunch Crunch, Yum!";
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
