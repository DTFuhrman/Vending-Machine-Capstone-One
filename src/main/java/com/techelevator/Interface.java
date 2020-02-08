package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interface {

	public Scanner keyboard = new Scanner(System.in);
	

	public void printMenu(String[] menu) {
		for (String str : menu) {
			if (!str.startsWith("h")) {
				System.out.println(str);
			}
		}
	}
	
	public void printCurrentStock(Map<String, VendItem> stock) {
		for (Map.Entry<String, VendItem> entry : stock.entrySet()) {
				if(entry.getValue().getNumberAvailable()<1) {
				System.out.println("::SOLD OUT:: " + entry.getValue());		
				} else {
				System.out.println(":::: " + entry.getKey() + " ::::: " + entry.getValue());
				}
		}
	}
	

	public void printCurrentBalance(int current) {
		System.out.println(">>Current Money Provided: $" + current/100 + "." + current%100 + ">");
	}
	
	
	public String getInput() {
			String output = keyboard.nextLine();
			return output;
		}


	public void pauseScrolling() {
		System.out.println("Press enter to continue");
		String userInput = getInput();
		if (userInput.toUpperCase().contains("Q")){
			System.exit(1);
		}
	}
	
	
}
