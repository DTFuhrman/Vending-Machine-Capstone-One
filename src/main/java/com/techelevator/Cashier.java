package com.techelevator;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class Cashier {

	private List<Double> income;
	private Map<String, Double> salesReport;
	private double currentBalance;
	
	
	
	public void feedMoney() {
	
		//10% chance it will reject bill (new feature?)
		//update this.currentBalance
	}
	
	public boolean purchaseMade() {
		boolean vended = true;
		//reset this.currentBalance
		//update this.income
		return vended;
	}
	
	public int[] giveChange(double amount) { 
		int[] change = new int[4];
		
		
		return change;
	}
	
	//fixme
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
