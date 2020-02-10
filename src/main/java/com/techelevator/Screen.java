package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Screen {

	// This is a very simple class with a scanner and some methods
	// for getting user input through the keyboard and printing information
	// to the console. Without this UI the controller would need to be 
	// rewired to output it's functionality differently, but mostly
	// things would remain the same. No real data or logic lives here.
	
	//The reason I am passing in generic parameters here is to avoid
	//Having the interface need to know anything about the logic or the data
	
	//Heres our scanner for listening to the keyboard
	public Scanner keyboard = new Scanner(System.in);

	
	// this helper function lets other classes use the scanner very quickly
	// that way this class doesn't need to know anything about the data
	// or the logic.
	public String getInput() {
		String output = keyboard.nextLine();
		return output;
	}

	// this helper function stops the menus from scrolling by too quickly
	// by requiring the enter key be hit.
	public String pauseScrolling() {
		System.out.println("Press enter to continue");
		String userInput = getInput();
		return userInput;
	}

	//All this does is format the current balance to display it to the user
	//In retrospect we should have made a more general function to convert any int 
	//that was a value in pennies into a string.
	public void printCurrentBalance(int current) {
		System.out.println(">>Current Money Provided: $" + current / 100 + "." + current % 100 + ">");
	}
	
	//This method takes a menu, all of which we have stored in arrays of strings,
	// and prints it to the console.
	public void printMenu(String[] menu) {
		for (String str : menu) {
			if (!str.startsWith("h")) {
				System.out.println(str);
			}
		}
	}

	// This is the most complex method in this class. 
	// It is designed to print out our CURRENT inventory,
	// which is stored in a map.
	public void printCurrentStock(Map<String, VendItem> stock) {
		for (Map.Entry<String, VendItem> entry : stock.entrySet()) {
			if (entry.getValue().getNumberAvailable() < 1) {
				System.out.println("::SOLD OUT:: " + entry.getValue());
			} else {
				System.out.println(":::: " + entry.getKey() + " ::::: " + entry.getValue());
			}
		}
	}	
}
