package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Machine {

	private Scanner keyboard = new Scanner(System.in);
	protected Cashier machineBank;
	protected Stock machineStock;
	protected CustomerUX machineUX;
	// these are our menus
	protected final Map<String, String> MAIN_MENU=new HashMap<String,String>(){{put("(1)","Display Vending Machine Items");put("(2)","Purchase");put("(3)","Exit");
	// The H means hidden, and it won't display
	put("(4)H","Sales Report");put("(5)H","Security Log");
	// new test functionality
	put("(6)H","Vender Order");}};
	protected final Map<String, String> PURCHASE_MENU=new HashMap<String,String>(){{put("(1)","Feed Money");put("(2)","Select Product");put("(3)","Finish Transaction");put("(4)","Back");}};
	protected final Map<String, String> SECURITY_MENU=new HashMap<String,String>(){{put("(1)","Upcoming Feature!");}};
	protected final Map<String, String> SALES_MENU=new HashMap<String,String>(){{put("(1)","Upcoming Feature!");}};
	protected final Map<String, String> ORDER_MENU=new HashMap<String,String>(){{put("(1)","Upcoming Feature!");}};

	// make complex constructor
	public Machine(String stockPath) {
		machineBank = new Cashier();
		machineStock = new Stock(stockPath);
	}

	// controller method
	public String makeSelection(String selection) {
		String[] input = selection.split("|");
		String menu = input[0];
		String action = input[1];
		String userInput = "";
		
	if (action.contentEquals("Display Vending Machine Items")) {
		machineStock.displayItems();
		menu =  "main";
	}

	if (action.contentEquals("Purchase")) {
		System.out.println("You selected purchase!\n\n");
		menu =  "purchase";
	}

	if (action.contentEquals("Exit")) {
		System.out.println("Exiting the current menu\n\n");
		menu =  "ux";
	}

	if (action.contentEquals("Sales Report")) {
		System.out.println("Exiting the current menu\n\n");
		menu =  "sales";
	}
	
	if (action.contentEquals("Security Log")) {
		menu =  "security";
	}
	
	if (action.contentEquals("Vendor Order")) {
		menu =  "vendor";
	}
	
	if (action.contentEquals("Feed Money")) {
		System.out.println("Please insert bills.");
		System.out.println("Then tell me how much.");
		userInput = keyboard.nextLine();
		boolean thatWorked = machineBank.feedMoney(Integer.parseInt(userInput));
		if (thatWorked) {System.out.println("Alright!");
		menu = "purchase";
	}
	
	if (action.contentEquals("Finish Transaction")) {
		System.out.println("Alright! I owe you " + Integer.toString(machineBank.getCurrentBalance()) + "!");
		int[] change = machineBank.getChange();
		System.out.println("Here is your change! " + Integer.toString(change[0]) + " Quarters, " + Integer.toString(change[0]) + " Dimes, and " + Integer.toString(change[0]) + " Nickels!");
		System.out.println("Transaction is complete, enjoy!");
		menu = "main";
	}
	
	if (action.contentEquals("Back")) {
		System.out.println("Returning to main menu");
		menu = "menu";
	}
	
	return menu;
}

	// menu method
	public String launchMenu(String toDisplay) {
		List<String> keys = new ArrayList<String>();
		String userInput = "";
		String menu = "";
		String selection = "";
		// display menu
		// depending on string, point to Map
		if (toDisplay.contentEquals("main")) {
			menu = "main";
			for (Map.Entry<String, String> entry : MAIN_MENU.entrySet()) {
				if (!entry.getKey().contains("H")) {
					System.out.println("::: " + entry.getKey() + " ::: " + entry.getValue());
				}
			}
		}
		// get input from user
		userInput = keyboard.nextLine();

		// if the key contains the input it will display the menu
		if (toDisplay.containsKey("(" + userInput + ")") || toDisplay.containsKey("(" + userInput + ")H")) {
			selection = toDisplay.get(userInput);
		}

		// if not it will display an error and relaunch the menu
		else {
			System.out.println("That's not on the menu!\n\n\n\n");
			selection = "Try Again";
		}
		return selection;
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
			launchMenu(MAIN_MENU);
		}
	}

	public String acceptInput(String input) {
		return "";
	}

}
