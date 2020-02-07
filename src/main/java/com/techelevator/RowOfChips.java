package com.techelevator;

public class RowOfChips extends VendItem {

	//this constructor creates the snack per the superclass constructor
	//and sets the behavior of the alert based on the child class type
	public RowOfChips(String name, int price) {
		super(name, price);
		super.setAlert("Crunch Crunch, Yum!");
	}

	
	
}
