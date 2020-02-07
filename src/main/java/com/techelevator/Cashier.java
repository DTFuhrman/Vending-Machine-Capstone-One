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
	
	public boolean purchaseMade(Map<String, VendItem> current, String location) {
		boolean vended = false;
		//
		if (current.get(location).getNumberAvailable() > 0) {
			current.get(location).decrimentNumber();
			// reduce currentBalance by price
			this.currentBalance -= current.get(location).getPriceInCents();
			// update this.income
			incomeInCents.add(current.get(location).getPriceInCents());
			vended = true;
		}
		return vended;
	}

	
	//check this
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

	public void printToLog(String output) {
		File report = new File(getDateForFileNames() + " - Sales Report");

	}

}
