package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Stock {
	

	// // read new stock file / current stock file
	// public static void main(String[] args) throws IOException {
	// 	String fileName = "log.txt";
	// 	File baseStock = new File(fileName);
		
	// 	//this will print to the new file. 
	// 	try (PrintWriter writer = new PrintWriter(baseStock)) {
	// 		String vendingMachine = "";

	// 	} catch (FileNotFoundException e) {
	// 		System.out.println("Invalid Entry, Please Make Another Selection!");

	// 	}


	//We have a base stock file that represents the main inventory we load from
	private File baseStock;
	//We also have a map that keeps track of the slot location
	//and the row object in that location
	protected Map<String, VendItem> machineProducts;

	//This constructor will be called by the VendingMachine Class
	//because each machine will have a stock, a cashier, and a UX
	//The constructor fills the stock fully from the original file
	//But it could be updated to track the stock through persistent memory by changing
	//which file it reads from.
	public Stock(String path) {
		this.baseStock = getFile(path);
		getStock(this.baseStock);
	}
	
	//this is a helper method to validate a path 
	//before we start pulling from it or writing to it
	//right now it ensures the file exists
	//but it can be ALTERED to throw a message if the file exists or expect the file to exist
	public File getFile(String path) {

		File fileToRead = new File(path);
		
		if (!fileToRead.exists()) { // checks for the existence of a file
			System.out.println(path + "... I can't find that file");
			//call UX to handle this and ask again
			System.exit(1);
		} else if (!fileToRead.isFile()) {//checks to make sure it's not a directory
			System.out.println(path + " is actually not a file");
			//call UX to handle this and ask again
			System.exit(1);
		}

		return fileToRead;
	}

	//This helper method fills the machine when it initializes
	//it is only called by the constructor, so it is private
	private void getStock(File inputFile) {
		machineProducts = new TreeMap<String, VendItem>();
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] tempItemDetails = line.split("\\|");
				String priceString = tempItemDetails[2];
				Double priceDoublePennies = Double.parseDouble(priceString);
				int price = priceDoublePennies.intValue();
				VendItem newItem;
				if (tempItemDetails[3].toUpperCase().contains("BEVERAGE")) {
					newItem = new RowOfBeverage(tempItemDetails[1],
							price);
				} else if (tempItemDetails[3].toUpperCase().contains("CHIP")) {
					newItem = new RowOfChips(tempItemDetails[1],
							price);
				} else if (tempItemDetails[3].toUpperCase().contains("GUM")) {
					newItem = new RowOfGum(tempItemDetails[1],
							price);
				} else {
					newItem = new RowOfCandy(tempItemDetails[1],
							price); 
					}
				//make it five instead
				machineProducts.put(tempItemDetails[0], newItem);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update stock if an item is sold
	public void removeFromStock(String key) {
		VendItem adjust = machineProducts.get(key);
		adjust.decrimentNumber();
		machineProducts.put(key, adjust);
	}
	
	//this returns the details so we can display them
	public Map<String, VendItem> getStockDetails() {
		Map<String, VendItem> temp = new TreeMap<String, VendItem>();
		temp.putAll(machineProducts);
		return temp;
	}
	
	public void displayStock() {
		for (Map.Entry<String, VendItem> entry : machineProducts.entrySet()) {
			System.out.println("::: " + entry.getKey() + " ::: " + entry.getValue());
		}
	}
}