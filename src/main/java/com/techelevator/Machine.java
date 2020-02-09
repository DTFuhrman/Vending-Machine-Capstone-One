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
		int index = 0;
		for (int value : values) {
			while (changeDue > value) {
				changeDue -= value;
				change[index] += 1;
			}
			index++;
		}
		data.setCurrentBalance(-data.getCurrentBalance());

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
		ux.pauseScrolling();
		launchMain();
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
				launchVend();
			} else if (purchaseInput.contains("4")) {
				launchCancel();
			}
		}
	}

	private void launchSelect() {
		Map<String, VendItem> items = data.getStockDetails();
		while (true) {
			ux.printCurrentStock(items);

			System.out.println("*************************************************");
			ux.printCurrentBalance(data.getCurrentBalance());
			System.out.println("Make your selection, or (cancel)");
			String selection = ux.getInput().toUpperCase();
			if (items.containsKey(selection) && items.get(selection).getNumberAvailable() > 0) {
				data.setCurrentSelection(selection);
				System.out.println("You Selected " + data.getCurrentSelection() + "!");
				System.out.println("Solid Choice!");
				ux.pauseScrolling();
				launchPurchase();
			} else if (items.containsKey(selection)) {
				System.out.println("SOLD OUT, Make new selection");
			} else if (selection.toUpperCase().contains("CAN")) {
				System.out.println("Returning to Purchase Menu");
				launchPurchase();
			} else {
				System.out.println("Please make a valid selection");
			}
		}
	}

	// this is checking the current stock for the items in the vending machine.
	// this will update once a purchase is made, it will also know the row the
	// product is in
	// the price and the type of product for purchase.
	public boolean purchaseMade(String key) {
		boolean vended = false;
		//
		if (data.checkStock(key) && data.getCurrentBalance() > data.getPrice(key)) {
			data.decrimentStock(key);
			// reduce currentBalance by price
			data.setCurrentBalance(-data.getPrice(key));
			// update this.income, we will update the current balance of the vending machine
			data.deposits.add(data.getPrice(key));
			// print to log
			vended = true;
		}
		return vended;
	}
	
	private void launchVend() {
		// If the current balance is greater than the price of the current selection
		if (data.getCurrentBalance() > data.getPrice(data.getCurrentSelection())) {
			//remind the user what they selected
		System.out.println("You selected " + data.getCurrentSelection());
			//tell them how much that costs
		int cost = data.getPrice(data.getCurrentSelection());
		System.out.println("That costs $" + cost/100 + "." + cost%100);
		System.out.println("You had " + data.getCurrentBalanceAsString());
		data.setCurrentBalance(-data.getPrice(data.getCurrentSelection()));
		data.addDeposit(data.getPrice(data.getCurrentSelection()));
		data.decrimentStock(data.getCurrentSelection());
		//write to log
		System.out.println("You now have " + data.getCurrentBalanceAsString());
		System.out.println("CCA-CHUNK CLINK");
		System.out.println(data.currentStockList.get(data.getCurrentSelection()).getDispenseAlert());
		System.out.println("*******************");
		System.out.println("*******************\n\n\n");
		} else {
			int stillOwed = data.getPrice(data.getCurrentSelection()) - data.getCurrentBalance();
			String strStillOwed = "$" + stillOwed/100 + "." + stillOwed%100;
			System.out.println("Please insert " + strStillOwed);
			launchFeed();
		}
	}

	private void launchCancel() {
		
		getChange();
		
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
		} else {
		System.out.println("That didn't work");
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