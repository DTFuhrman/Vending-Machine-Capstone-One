package com.techelevator;

public class MVC {

	public static void main(String[] args) {

		Controller main = new Controller("vendingmachine.csv");
		while(true) {
		main.launcher();
		}
	}
}
