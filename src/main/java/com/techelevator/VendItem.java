package com.techelevator;

public abstract class VendItem {

	//This class is the abstract basis for our vending machine rows
	//Each row (in the z direction) will have a number of treats left
	//the name of the product in the row
	//the noise it makes when it vends
	//and the price (stored in cents as an integer
	private int numberOf;
	private String productName;
	//our alert is protected instead of private so we can set it in the child class
	protected String alert;
	private int priceInCents;
	private String slotID;
	
	
	//our constructor requires the name of the product and the price
	//it initializes in the number available to five regardless of what it is
	//and the noise it makes is decided down the inheritance tree
	//by the type of item
	public VendItem(String name, int price) {
		this.numberOf = 5;
	}
	
	//This method will return the noise it is supposed to make when it vends
	public String getDispenseAlert() {
		return this.alert;
	}
	
	//this method will get the name of the product
	public String getName() {
		return this.productName;
	}
	
	//This is protected to allow the constructor 
	//in the child class to set the alert 
	protected void setAlert(String noise) {
		this.alert = noise;
	}
	
	//This is a getter for NumberOf
	public int getNumberAvailable() {
		return this.numberOf;
	}
	
	//This is a method of decreasing our stock in a row
	//so that when an item is purchased the cashier can 
	//remove the item
	public void decrimentNumber() {
		this.numberOf--;
	}
	
	//We chose to do all of our money in cents as ints 
	//because this will avoid rounding errors
	//and we do not expect to ever collect more than 2.1 billion cents
	//Just in case, we should create a redundancy in the cashier.
	public int getPriceInCents() {
		return this.priceInCents;
	}

	@Override
	public String toString() {
		return "We have " + numberOf + " of " + productName + " available for $" + priceInCents/100 + "." + priceInCents%100;
	}
	
	
}
