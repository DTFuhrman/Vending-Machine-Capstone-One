package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Stock {
	Map<String, Integer> machineProducts = new HashMap<String, Integer>();

	// read new stock file / current stock file
	public static void main(String[] args) throws IOException {
		String fileName = "log.txt";
		File baseStock = new File(fileName);
		
		//this will print to the new file. 
		try (PrintWriter writer = new PrintWriter(baseStock)) {
			String vendingMachine = "";

		} catch (FileNotFoundException e) {
			System.out.println("Invalid Entry, Please Make Another Selection!");

		}


		// create current stock file

		// update current stock file

		// delete machine stock
	}
}