package com.techelevator;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class Cashier {

	private List<Integer> incomeInCents;
	private Map<String, Integer> salesReport;
	private int currentBalance;

	
	
	// this will accept the  valid bills entered into the machine, we are counting with pennies.
	//going from $1 - $20, if a bill that is not one of those, it will be rejected. we will also 
	//have a 10% fail rate such as the bill is creased too much and it will need to be entered again. 
	public boolean feedMoney(int input) {
		boolean acceptedBill = false;
		if (input == 100 || input == 200 || input == 500 || input == 1000 || input == 2000) {
			this.currentBalance += input;
			acceptedBill = true;
		}
		return acceptedBill;
	}
	
	

	public int getCurrentBalance() {
		return this.currentBalance;
	}
	
	//this is checking the current stock for the items in the vending machine. 
	//this will update once a purchase is made, it will also know the row the product is in
	//the price and the type of product for purchase. 
	public boolean purchaseMade(Map<String, VendItem> current, String location) {
		boolean vended = false;
		//
		if (current.get(location).getNumberAvailable() > 0) {
			current.get(location).decrimentNumber();
			// reduce currentBalance by price
			this.currentBalance -= current.get(location).getPriceInCents();
			// update this.income, we will update the current balance of the vending machine 
			incomeInCents.add(current.get(location).getPriceInCents());
			vended = true;
		}
		return vended;
	}

	
	//check this
	//this will check for the change that the user will get back. 
	//the user will only get back nickels, dimes, and quarters.
	public int[] getChange(int amount) {
		int[] change = new int[3];
		int[] values = new int[] { 25, 10, 5 };
		int amountInPennies = amount;
		int remainder = amountInPennies;
		int index = 0;
		for (int value : values) {
			while (remainder > value) { 
					remainder -= value;
					change[1] += 1;
			}
			index++;
		}
		return change;
	}

	// fixme
	//this will track the log of purchases 
	//what was purchased, the date, and the time, also what bill was used for purchase. 
	private String getDateForFileNames() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		return dateTime;
	}

	public File generateSalesReport() {
		File report = new File(getDateForFileNames() + " - Sales Report");

		return report;
	}

	//this will print to the file the report for sales of products 
	public void printToLog(String output) {
		File report = new File(getDateForFileNames() + " - Sales Report");

	}

}
