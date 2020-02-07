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
	
	//This will allow the constructor 
	protected void setAlert(String noise) {
		this.alert = noise;
	}
	
	public int getNumberAvailable() {
		return this.numberOf;
	}
	
	public void decrimentNumber() {
		this.numberOf--;
	}
	
	public int getPriceInCents() {
		return this.priceInCents;
	}
}
