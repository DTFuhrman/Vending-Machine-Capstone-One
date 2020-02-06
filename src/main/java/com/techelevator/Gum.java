package com.techelevator;

public class Gum implements Vendable {

	String name;
	String alert;
	
	public Gum(String name) {
		this.alert = "Chew Chew, Yum!";
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
