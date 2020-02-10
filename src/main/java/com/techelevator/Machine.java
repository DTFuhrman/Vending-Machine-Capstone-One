package com.techelevator;

import java.util.Map;

public class Machine {

	private Memory data;
	private Screen ux;
	
	public Machine(String path) {
		data = new Memory(path);
		ux = new Screen();
	}

	// check this
	// this will check for the change that the user will get back.
	// the user will only get back nickels, dimes, and quarters.
	public int[] getChange() {
		int[] change = new int[3];
		int[] values = new int[] { 25, 10, 5 };
		int changeDue = data.getCurrentBalance();
		data.setCurrentBalance(-data.getCurrentBalance());
		data.logChange(changeDue);
		int index = 0;
		for (int value : values) {
			while (changeDue >= value) {
				changeDue -= value;
				change[index] += 1;
			}
			index++;
		}

		System.out.println("Here is your change:");
		if (change[0]==1) {System.out.println("1 Quarter");}
		else {System.out.println("and " + change[0] + " Quarters!");}
		if (change[1]==1) {System.out.println("1 Dime");}
		else {System.out.println("and " + change[1] + " Dimes!");}
		if (change[2]==1) {System.out.println("and 1 Nickle!");}
		else {System.out.println("and " + change[2] + " Nickles!");}
		
		return change;
	}


	// this will accept the valid bills entered into the machine, we are counting
	// with pennies.
	// going from $1 - $20, if a bill that is not one of those, it will be rejected.
	// we will also
	// have a 10% fail rate such as the bill is creased too much and it will need to
	// be entered again.

	// ********** Launchers ********

	public void launcher() {
		String userInput;
		ux.printMenu(data.getMenu(0));
		adminOptions(ux.pauseScrolling());
		launchMain();
	}
	
	public void adminOptions(String input) {
		if(input.toUpperCase().contains("Q")) {
			data.logShutDown();
			System.exit(1);
		}
	}
	
	public void launchMain() {
		while (true) {
			String mainInput;
			ux.printMenu(data.getMenu(1));
			mainInput = ux.getInput();
			if (mainInput.contains("1")) {
				ux.printCurrentStock(data.getStockDetails());
				ux.pauseScrolling();
			} else if (mainInput.contains("2")) {
				launchPurchase();
			} else if (mainInput.contains("3")) {
				launcher();
			} else if (mainInput.contains("4")) {
				launchSale();
			} else if (mainInput.contains("5")) {
				LaunchOrder();
			} else if (mainInput.contains("0")) {
				launchLog();
			} else {
				System.out.println("please make a valid selection");
			}
		}
	}

	private void launchPurchase() {
		while (true) {
			ux.printMenu(data.getMenu(2));
			System.out.println(">>>>>>>>>>>>>");
			ux.printCurrentBalance(data.getCurrentBalance());
			String purchaseInput = ux.getInput();
			if (purchaseInput.contains("1")) {
				launchFeed();
			} else if (purchaseInput.contains("2")) {
				launchSelect();
			} else if (purchaseInput.contains("3")) {
				launchFinishTransaction();
			} else {
				System.out.println("please make a valid selection");
			}
		}
	}

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
			//record their input in uppercase
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


	private void launchFinishTransaction() {
		if (data.getCurrentBalance() > 0){
			getChange();
		}
		System.out.println("Returning To Main Menu");
		ux.pauseScrolling();
		launchMain();
	}

	private void launchFeed() {
		System.out.println("We accept $1, $2, $5, $10 and $20 bills");
		String stringInput =  ux.getInput();
//		if(stringInput.replaceAll("", replacement))
//		stringInput = stringInput.
		int input = Integer.parseInt(stringInput) * 100;
		if (input == 100 || input == 200 || input == 500 || input == 1000 || input == 2000) {
			data.setCurrentBalance(input);
			System.out.println("You entered $" + stringInput);
			data.logFeed(input, data.getCurrentBalance());
		} else {
		System.out.println("That didn't work");
		data.logFeedFailure(input, data.getCurrentBalance());
		}
	}

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