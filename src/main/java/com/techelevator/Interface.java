package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interface {

	// This is a very simple class with a scanner and some methods
	// for getting user input through the keyboard and printing information
	// to the console. Without this UI the controller would need to be 
	// rewired to output it's functionality differently, but mostly
	// things would remain the same. No real data or logic lives here.
	
	//Heres our scanner for listening to the keyboard
	public Scanner keyboard = new Scanner(System.in);

	
	//this helper function lets other classes use the scanner very quickly
	public String getInput() {
		String output = keyboard.nextLine();
		return output;
	}

	// this helper function stops the menus from scrolling by too quickly
	// by requiring the enter key be hit.
	// it also gives a hidden exit from the program.
	// It could be extended later to create a secret admin mode to
	// See all the hidden menus and access extra functionality.
	// I know old vending machines had that.
	public void pauseScrolling() {
		System.out.println("Press enter to continue");
		String userInput = getInput();
		if (userInput.toUpperCase().contains("Q")) {
			System.exit(1);
		}
	}

	//All this does is format the current balance to display it to the user
	public void printCurrentBalance(int current) {
		System.out.println(">>Current Money Provided: $" + current / 100 + "." + current % 100 + ">");
	}
	
	public void printMenu(String[] menu) {
		for (String str : menu) {
			if (!str.startsWith("h")) {
				System.out.println(str);
			}
		}
	}

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
