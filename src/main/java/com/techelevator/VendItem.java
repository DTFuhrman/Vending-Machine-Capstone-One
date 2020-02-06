package com.techelevator;

public abstract class VendItem {

	protected int numberOf;
	private String productName;
	protected String alert;
	private int priceInCents;
	
	public VendItem(String name, int price) {
		this.numberOf = 5;
	}
	
	public String getDispenseAlert() {
		return this.alert;
	}
	
	public String getName() {
		return this.productName;
	}
	
	protected void setAlert(String noise) {
		this.alert = noise;
	}
	
	public void decrimentNumber() {
		this.numberOf--;
	}
	
	public int getPriceInCents() {
		return this.priceInCents;
	}
}
