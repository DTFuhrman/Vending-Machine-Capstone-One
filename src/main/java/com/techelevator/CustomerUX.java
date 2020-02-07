package com.techelevator;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerUX {
		
	Scanner keyboard = new Scanner(System.in);
	Map<String, String> mainMenu = new HashMap<String, String>() {{
		put("(1)", "Display Vending Machine Items");
		put("(2)", "Purchase");
		put("(3)", "Exit");
		//The H means hidden, and it won't display
		put("(4)H", "Sales Report");
		put("(5)H", "Security Log");
		//new test functionality
		put("(6)H", "Vender Order");
	}};
	
	Map<String, String> purchaseMenu = new HashMap<String, String>() {{
		put("(1)", "Feed Money");
		put("(2)", "Select Product");
		put("(3)", "Finish Transaction");
		put("(4)", "Back");
	}};
	
	public boolean launchMenu(Map<String, String> toDisplay) {
		List<String> keys = new ArrayList<String>();
		boolean launched = true;
		String userInput = "stay";
		String menuItem = "";
		//display menu 
		for (Map.Entry<String, String> entry : toDisplay.entrySet()) {
			if (!entry.getKey().contains("H")) {
			System.out.println("::: " + entry.getKey() + " ::: " + entry.getValue());
			}
		}
		
		//get input from user
		userInput = keyboard.nextLine();
		
		//if the key contains the input it will display the menu
		if (toDisplay.containsKey("(" + userInput + ")") || toDisplay.containsKey("(" + userInput + ")H")) {
			menuItem = toDisplay.get(userInput);
		}
		
		//if not it will display an error and relaunch the menu
		else {
			System.out.println("That's not on the menu!\n\n\n\n");
			launchMenu(toDisplay);
		}
		
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		if (menuItem.contentEquals("Feed Money")) {}
		
		return launched;
	}
	
// might need this for infinite loop
// and for idle or welcome screen	
//	public void uxLauncher() {
//		
//	}
	
	public void displayItems() {
		
	}
	
	public String acceptInput(String input) {
		return "";
	}
	
	
		
	
}
