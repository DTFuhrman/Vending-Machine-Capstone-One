package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MVC {

	public static void main(String[] args) {

		Controller main = new Controller("vendingmachine.csv");
		while(true) {
		main.launcher();
		}
	}
	
	
}