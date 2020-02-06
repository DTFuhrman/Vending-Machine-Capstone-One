package com.techelevator;

public class Candy implements Vendable {

	String name;
	String alert;
	
	public Candy(String name) {
		this.alert = "Munch Munch, Yum!";
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
