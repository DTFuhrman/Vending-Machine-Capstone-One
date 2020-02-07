package com.techelevator;

public class RowOfGum extends VendItem {

	//this constructor creates the snack per the superclass constructor
	//and sets the behavior of the alert based on the child class type
	public RowOfGum(String name, int price) {
		super(name, price);
		super.setAlert("Chew Chew, Yum!");
	}

	
	
}
