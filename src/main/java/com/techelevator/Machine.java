package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Machine {

	protected Cashier machineBank;
	protected Stock machineStock;
	protected CustomerUX machineUX;
	

	// make complex constructor
	public Machine(String stockPath) {
		machineBank = new Cashier();
		machineStock = new Stock(stockPath);
		machineUX = new CustomerUX();
	}

	
	
	
	// controller method
//	public String controller(String menu, String action) {
//		
//		
//	if (action.contentEquals("Display Vending Machine Items")) {
//		machineStock.displayItems();
//		menu =  "main";
//	}
//
//	if (action.contentEquals("Purchase")) {
//		System.out.println("You selected purchase!\n\n");
//		menu =  "purchase";
//	}
//
//	if (action.contentEquals("Exit")) {
//		System.out.println("Exiting the current menu\n\n");
//		menu =  "ux";
//	}
//
//	if (action.contentEquals("Sales Report")) {
//		System.out.println("Exiting the current menu\n\n");
//		menu =  "sales";
//	}
//	
//	if (action.contentEquals("Security Log")) {
//		menu =  "security";
//	}
//	
//	if (action.contentEquals("Vendor Order")) {
//		menu =  "vendor";
//	}
//	
//	if (action.contentEquals("Feed Money")) {
//		System.out.println("Please insert bills.");
//		System.out.println("Then tell me how much.");
//		userInput = keyboard.nextLine();
//		boolean thatWorked = machineBank.feedMoney(Integer.parseInt(userInput));
//		if (thatWorked) {System.out.println("Alright!");
//		menu = "purchase";}
//	}
//	
//	if (action.contentEquals("Finish Transaction")) {
//		System.out.println("Alright! I owe you " + Integer.toString(machineBank.getCurrentBalance()) + "!");
//		int[] change = machineBank.getChange();
//		System.out.println("Here is your change! " + Integer.toString(change[0]) + " Quarters, " + Integer.toString(change[0]) + " Dimes, and " + Integer.toString(change[0]) + " Nickels!");
//		System.out.println("Transaction is complete, enjoy!");
//		menu = "main";
//	}
//	
//	if (action.contentEquals("Back")) {
//		System.out.println("Returning to main menu");
//		menu = "menu";
//	}
//	
//	return menu;
//}

	

	

	public String acceptInput(String input) {
		return "";
	}

}
