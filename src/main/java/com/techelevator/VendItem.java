package com.techelevator;

public abstract class VendItem {


	String productName;
	String alert;
	String slotLocation;
	int priceInCents;
	
	public VendItem(String name, String slotLocation, int price) {
		
	}
	
	public String getDispenseAlert() {
		return this.alert;
	}
	
	public String getName() {
		return this.productName;
	}
}
