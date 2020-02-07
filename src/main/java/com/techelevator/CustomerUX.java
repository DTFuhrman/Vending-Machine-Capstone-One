package com.techelevator;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerUX {

	public Scanner keyboard = new Scanner(System.in);
	// these are our menus
	protected final Map<String, String> MAIN_MENU = new HashMap<String, String>() {
		{
			put("(1)", "Display Vending Machine Items");
			put("(2)", "Purchase");
			put("(3)", "Exit");
			// The H means hidden, and it won't display
			put("(4)H", "Sales Report");
			put("(5)H", "Security Log");
			// new test functionality
			put("(6)H", "Vender Order");
		}
	};
	protected final Map<String, String> PURCHASE_MENU = new HashMap<String, String>() {
		{
			put("(1)", "Feed Money");
			put("(2)", "Select Product");
			put("(3)", "Finish Transaction");
			put("(4)", "Back");
		}
	};
	protected final Map<String, String> SECURITY_MENU = new HashMap<String, String>() {
		{
			put("(1)", "Upcoming Feature!");
		}
	};
	protected final Map<String, String> SALES_MENU = new HashMap<String, String>() {
		{
			put("(1)", "Upcoming Feature!");
		}
	};
	protected final Map<String, String> ORDER_MENU = new HashMap<String, String>() {
		{
			put("(1)", "Upcoming Feature!");
		}
	};

	
	
	
	public void printMenu(Map<String, String> menu) {
		// menu method

		for (Map.Entry<String, String> entry : menu.entrySet()) {
			if (!entry.getKey().contains("H")) {
				System.out.println("::: " + entry.getKey() + " ::: " + entry.getValue());
			}
		}
	}

	public String getInput() {
			String output = keyboard.nextLine();
			return output;
		}

	
	public void uxLauncher() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$                            $$");
		System.out.println("$$         Welcome to         $$");
		System.out.println("$$       VendoMatic-8000      $$");
		System.out.println("$$        Press (enter)       $$");
		System.out.println("$$    to start your journey   $$");
		System.out.println("$$                            $$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$                            $$");
		System.out.println("$$    awaiting user input     $$");
		System.out.println("$$                            $$");
		System.out.println("$$                            $$");

		String input = keyboard.nextLine();
		if (input.toUpperCase().equals("Q") || input.equals("QUIT")) {
			System.exit(1);
		} else {
			printMenu(MAIN_MENU);
		}
	}
}

///discarded code




//String userInput = "";
//String menu = "";
//String selection = "";
// display menu
// depending on string, point to Map

// get input from user
//userInput = keyboard.nextLine();
//
//// if the key contains the input it will display the menu
//if (toDisplay.containsKey("(" + userInput + ")") || toDisplay.containsKey("(" + userInput + ")H")) {
//	selection = toDisplay.get(userInput);
//}
//
//// if not it will display an error and relaunch the menu
//else {
//	System.out.println("That's not on the menu!\n\n\n\n");
//	selection = "Try Again";
//}
//return selection;
//}	
