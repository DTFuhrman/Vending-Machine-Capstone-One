package com.techelevator;

public class RowOfBeverage extends VendItem {

	
	//this constructor creates the snack per the superclass constructor
	//and sets the behavior of the alert based on the child class type
	public RowOfBeverage(String name, int price) {
		super(name, price);
		super.setAlert("Glug Glug, Yum!");
	}
	
}
