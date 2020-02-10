package com.techelevator;

import java.util.Map;

public class Machine {
	//CONTROL *****************
		//This method mainly contains logic helper methods
		// and flow control methods that put the functions in order
		// so the user moves from one function to the next smoothly
	// This class instantiates our Memory(Data and data manipulation) 
	// and our Screen(User interface)
	// We tried to implement an MVC architecture and keep our
	// Control seperate from our data seperate from our interface.
	//This is helpful because we could rework the interface without
	// changing the underlying logic if we wanted to change how
	// we display, or if we wanted to migrate to a different 
	// kind of data management, we could rework our memory without
	// changing the logic much either.
	// The control instantiates the other two because that gives it 
	// access to all the functionality housed in the other classes.
	private Memory data;
	private Screen ux;
	
	//Constructor
	//pretty simple. Most of the heavy lifting is done deeper in
	public Machine(String path) {
		data = new Memory(path);
		ux = new Screen();
	}

	//GETTERS and SETTERS *************
	//Nothing to get or set. This class is mainly for control
	//so it instantiates the other classes. 
	
	//HELPER METHODS
	// this will check for the change that the user will get back.
	// the user will only get back nickels, dimes, and quarters.
	// We implemented a greedy algorithm, which we believe works
	// well with these values.
	public int[] getChange() {
		//we make an empty array for the change
		int[] change = new int[3];
		//we hard code an array of the values of each denomination
		int[] values = new int[] { 25, 10, 5 };
		//we check the current balance to see how much change to give
		int changeDue = data.getCurrentBalance();
		//We subtract all of the money from the current balance
		data.setCurrentBalance(-data.getCurrentBalance());
		//We log the fact that we are giving change
		data.logChange(changeDue);
		//We set an index equal to the first entry in our denominations
		int index = 0;
		//this loop will load our change array with amounts for each denomination
		for (int value : values) {
			while (changeDue >= value) {
				changeDue -= value;
				change[index] += 1;
			}
			index++;
		}
		// Let the user know what's going on
		// and give them each number and denomination of coin
		System.out.println("Here is your change:");
		if (change[0]==1) {System.out.println("1 Quarter");}
		else {System.out.println("and " + change[0] + " Quarters!");}
		if (change[1]==1) {System.out.println("1 Dime");}
		else {System.out.println("and " + change[1] + " Dimes!");}
		if (change[2]==1) {System.out.println("and 1 Nickle!");}
		else {System.out.println("and " + change[2] + " Nickles!");}
		
		return change;
	}

	// ********** Launchers ********
	// We were not sure what the industry term is but we called
	// these launchers.
	
	// These implement each bite sized chunk of functionality 
	// and call each other to manage the flow of the program
	// This bite sized chunk approach saves us from having a giant
	// untrimmed forest of if/than trees and makes the logic much 
	// easier to follow in each chunk even if it slightly obscures 
	// the overall flow. 

	
	//This is the only necessary function to control in main
	// It launches the welcome screen then moves on to the 
	// main menu once any input is recieved.
	public void launcher() {
		String userInput;
		ux.printMenu(data.getMenu(0));
		//This next line is confusing. The pause function asks for input and returns it
		// We normally use it just to pause the scrlling so the menus are
		// readable, but it does return the input in case we want to use it.
		// In this case we are running a second helper method which checks if 
		// the user wants to quit (q) or if the user knows the secret admin code 
		// POTENTIAL EXTENSIBILITY: The secret admin code is not yet implimented, but it would change 
		// the way menus are printed showing the hidden menus and allowing extra 
		// functionality. It would set a boolean flag and usse a seperate print menu method.
		adminOptions(ux.pauseScrolling());
		launchMain();
	}
	
	//This is the helper function that checks for admin status [not yet implemented]
	// and allows you to quit.
	public void adminOptions(String input) {
		if(input.toUpperCase().contains("Q")) {
			data.logShutDown();
			System.exit(1);
		}
//		if(input.toUpperCase().equals("admin")) {
//			data.setAdminFlag(true);
//		}
	}
	
	
	//This launcher runs the main menu in an infinite loop until a valid selection is made
	public void launchMain() {
		while (true) {
			String mainInput;
			//this function prints the menu for the user
			//and listens for input
			ux.printMenu(data.getMenu(1));
			mainInput = ux.getInput();
			//if the user selects the first item the current stock is printed to the screen
			//scrolling is paused until the user hits enter
			// and the loop repeats
			if (mainInput.contains("1")) {
				ux.printCurrentStock(data.getStockDetails());
				ux.pauseScrolling();
			//if the user selects the purchase menu it is launched
			} else if (mainInput.contains("2")) {
				launchPurchase();
			//if the user selects the exit option the program exits back to the welcome screen
			// this allows it to keep it's memory unless it is fully quit, which is not
			// normally an option users have unless they unplug the machine
			} else if (mainInput.contains("3")) {
				launcher();
	// The next few would launch the menus accessing the various report functions
	// but they are not yet implemented
//			} else if (mainInput.contains("4")) {
//				launchSale();
//			} else if (mainInput.contains("5")) {
//				LaunchOrder();
//			} else if (mainInput.contains("0")) {
//				launchLog();
	// If the user failed to make a valid selection they are prompted to and the menu repeats
	// We considered moving the printing of the menus outside the loops so that they were only 
	// printed once, but we weren't sure which behavior would be preferable for the user or product owner.
	// In the industry I am sure we'd have someone to ask that kind of question.
			} else {
				System.out.println("please make a valid selection");
			}
		}
	}

	
	//This launches the purchase menu
	// in an infinate loop
	private void launchPurchase() {
		while (true) {
			ux.printMenu(data.getMenu(2));
			System.out.println(">>>>>>>>>>>>>");
			//This prints the current balance under the menu as requested
			ux.printCurrentBalance(data.getCurrentBalance());
			// ask for input
			String purchaseInput = ux.getInput();
			// If the inpu t1 is selected the bill feed functionality is launched
			if (purchaseInput.contains("1")) {
				launchFeed();
			//If they select make a selection that process is launched
			} else if (purchaseInput.contains("2")) {
				launchSelect();
			//This launches finish transaction which gives them their change and returns them
			// to the main menu
			} else if (purchaseInput.contains("3")) {
				launchFinishTransaction();
				// If they don't make a valid selection we prompt them to and the menu repeats
			} else {
				System.out.println("please make a valid selection");
			}
		}
	}

	//This is the bill feed flow
	private void launchFeed() {
		// We let the user know what input we will accept and get input
		System.out.println("We accept $1, $2, $5, $10 and $20 bills");
		String stringInput =  ux.getInput();
		//We change the input from a string into an int representing cents
				//**** FIX SOON!!!! *******This is where our program might break
	//FIXED <	//**** We should verify above whether the string is an acceptable input
				//**** BEFORE we try to parse it.
		//It checks to see if you tried to give it change and removes the change
		if(stringInput.contains(".")) {
			System.out.println("We unfortunately can't accept change");
			stringInput = stringInput.substring(0, stringInput.indexOf("."));
		}
		stringInput = stringInput.replaceAll("[^0-9]", "");
		int input = Integer.parseInt(stringInput) * 100;
		// We compare the input to acceptable inputs to decide whether to accept or decline
		if (input == 100 || input == 200 || input == 500 || input == 1000 || input == 2000) {
			//if we accept we increase current balance
			data.setCurrentBalance(input);
			//we let the user know
			System.out.println("You entered $" + stringInput);
			//we log the successful feed
			data.logFeed(input, data.getCurrentBalance());
		//if their input parses but doesn't equal a whole bill
		} else {
			//we let them know
			System.out.println("That didn't work");
			// we log the failed attempt and let them fall back 
			// into the while loop in the earlier menu
			data.logFeedFailure(input, data.getCurrentBalance());
		}
	}
	
	//This launches the functionality of selecting an item and ending it
	private void launchSelect() {
		//enter menu loop
		while (true) {
			//grab an updated copy of the items currently available
			Map<String, VendItem> items = data.getStockDetails();
			//print the current stock
			ux.printCurrentStock(items);
			System.out.println("*************************************************");
			//print their current balance
			ux.printCurrentBalance(data.getCurrentBalance());
			//prompt the user to enter a selection or type cancel
			System.out.println("Make your selection, or (cancel)");
			//record their input in upper case
			String selection = ux.getInput().toUpperCase();
			//if the slot number exists AND the their selection is available
			if (items.containsKey(selection) && items.get(selection).getNumberAvailable() > 0) {
				//then set the current selection as that key
				data.setCurrentSelection(selection);
				//let the user know what they selected
				System.out.println("You Selected " + data.getCurrentSelection() + "!");
				System.out.println("Solid Choice!");
				
				//if the current balance is greater than the cost of the item
				if (data.getCurrentBalance() > data.getPrice(data.getCurrentSelection())) {
					//tell them how much that costs
				int cost = data.getPrice(data.getCurrentSelection());
				System.out.println("That costs $" + cost/100 + "." + cost%100);
					//tell them how much they had
				System.out.println("You had " + data.getCurrentBalanceAsString());
				//log the purchase
				data.logPurchase(data.getCurrentSelection(), items.get(data.getCurrentSelection()).getName(), data.getCurrentBalance(), data.getCurrentBalance()-cost);
				//subtract cost of item from current balance
				data.setCurrentBalance(-data.getPrice(data.getCurrentSelection()));
				//record the money in deposits
				data.addDeposit(data.getPrice(data.getCurrentSelection()));
				//decrease the number of the item available for sale
				data.decrimentStock(data.getCurrentSelection());
				//tell user their new balance
				System.out.println("You now have " + data.getCurrentBalanceAsString());
				//vend the item
				System.out.println("*******************");
				System.out.println("CCA-CHUNK CLINK");
				System.out.println(items.get(data.getCurrentSelection()).getDispenseAlert());
				System.out.println("*******************");
				System.out.println("*******************\n\n\n");
				//let them enjoy their purchase before returning them to the top of the selection menu
				ux.pauseScrolling();
				//if the balance ISN'T higher than the price
				} else {
					//Tell them how much they still owe
					int stillOwed = data.getPrice(data.getCurrentSelection()) - data.getCurrentBalance();
					String strStillOwed = "$" + stillOwed/100 + "." + stillOwed%100;
					//helpfully prompt them to give us more money
					System.out.println("Please insert " + strStillOwed);
					//give them time to read, then throw them back to the purchase menu, 
					//where they will hopefully give us more money
					ux.pauseScrolling();
					launchPurchase();
				}
				//if they typed cancel instead of a slot item, return them to the purchase menu
			} else if (selection.toUpperCase().contains("CAN")) {
				System.out.println("Returning to Purchase Menu");
				launchPurchase();
				//if the selection they made was not a valid key
				//ask them to make a new selection and return them to the top of the menu loop
			} else if (!items.containsKey(selection)) {
				System.out.println("Please make a valid selection or type cancel");
				//if their are not enough items for them to buy, let them know it's sold out and 
				//return them to the top of the menu loop
			} else if (!(items.get(selection).getNumberAvailable() > 0)) {
				System.out.println("SOLD OUT, Make new selection");
			}
		}
	}

	//If they select finish transaction we give them their change 
	// if they still have a current balance and we return them to 
	// the main menu.
	private void launchFinishTransaction() {
		if (data.getCurrentBalance() > 0){
			getChange();
		}
		System.out.println("Returning To Main Menu");
		ux.pauseScrolling();
		launchMain();
	}

	//
	private void launchLog() {
		while (true) {
			ux.printMenu(data.getMenu(3));
			String logInput = ux.getInput();
			if (logInput.contains("1")) {

			} else if (logInput.contains("2")) {

			} else if (logInput.contains("3")) {

			} else if (logInput.contains("4")) {

			} else if (logInput.contains("0")) {

			}
		}
	}

	private void launchSale() {
		ux.printMenu(data.getMenu(4));
		String saleInput = ux.getInput();
	}

	private void LaunchOrder() {
		ux.printMenu(data.getMenu(5));
		String orderInput = ux.getInput();
	}

}