package com.techelevator;

public class VendoMatic800 {

	public static void main(String[] args) {

		Machine mainMachine = new Machine("vendingmachine.csv");

		String input;
		String userChoice;
		String menu = "main";

		while (true) {
			mainMachine.machineUX.uxLauncher();
			while (true) {
				// print main menu
				mainMachine.machineUX.printMenu(mainMachine.machineUX.MAIN_MENU);
				input = mainMachine.machineUX.getInput();
				if (input.contains("1")) {
					mainMachine.machineStock.displayStock();
				} else if (input.contains("2")) {
					// print purchase menu
					// THIS MIGHT MESS US UP!
					while (Integer.parseInt(input) != 3) {
						mainMachine.machineUX.printMenu(mainMachine.machineUX.PURCHASE_MENU);
						input = mainMachine.machineUX.getInput();
						if (input.contains("1")) {
							System.out.println("Alright. Feed some money!");
							int moneyToFeed = Integer.parseInt(mainMachine.machineUX.getInput());
							boolean itWorked = mainMachine.machineBank.feedMoney(moneyToFeed);
							if (itWorked) {
								System.out.println("Cool! Current balance is "
										+ mainMachine.machineBank.getCurrentBalanceAsString());
							} else {
								System.out.println("That Didn't Work. Is your bill crumpled?");
							}
						} else if (input.contains("2")) {
							System.out.println("Go ahead");
							userChoice = mainMachine.machineUX.getInput();
							if (mainMachine.machineStock.machineProducts.containsKey(userChoice)
									&& mainMachine.machineStock.machineProducts.get(userChoice)
											.getNumberAvailable() > 0) {
								System.out.println("Good Choice!");
							} else if (mainMachine.machineStock.machineProducts.containsKey(userChoice)
									&& mainMachine.machineStock.machineProducts.get(userChoice)
											.getNumberAvailable() <= 0) {
								System.out.println("Sold Out!");
							} else {
								System.out.println("I didn't Recognize That Selection");
							}

						}
					}
				} else if (input.contains("3")) {
					break;
				}
			}
		}
	}
}
