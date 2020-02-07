package com.techelevator;

public class VendoMatic800 {

	public static void main(String[] args) {

		Machine mainMachine = new Machine("vendingmachine.csv");
		String selection;
		String choice;
		String menu = "MAIN_MENU";

		while (true) {
			mainMachine.machineUX.uxLauncher();
			while (true) {
				selection = mainMachine.machineUX.launchMenu(menu);
				menu = mainMachine.machineUX.makeSelection(selection);	
			}
		}
	}

}
