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


	// 	// create current stock file

	// 	// update current stock file

	private File baseStock;
	private Map<String, VendItem> machineProducts;

	public Stock(String path) {
		this.baseStock = getFile(path);
		getStock(this.baseStock);
	}
	
	public File getFile(String path) {

		File fileToRead = new File(path);
		
		if (!fileToRead.exists()) { // checks for the existence of a file
			System.out.println(path + "... I can't find that file");
			//call UX to handle this and ask again
			System.exit(1);
		} else if (!fileToRead.isFile()) {
			System.out.println(path + " is actually not a file");
			//call UX to handle this and ask again
			System.exit(1);
		}

		return fileToRead;
	}


	private void getStock(File inputFile) {
		machineProducts = new HashMap<String, VendItem>();
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] tempItemDetails = line.split("|");
				VendItem newItem;
				if (tempItemDetails[3].toUpperCase().contains("BEVERAGE")) {
					newItem = new RowOfBeverage(tempItemDetails[1],
							Integer.parseInt(tempItemDetails[2]));
				} else if (tempItemDetails[3].toUpperCase().contains("CHIP")) {
					newItem = new RowOfChips(tempItemDetails[1],
							Integer.parseInt(tempItemDetails[2]));
				} else if (tempItemDetails[3].toUpperCase().contains("GUM")) {
					newItem = new RowOfGum(tempItemDetails[1],
							Integer.parseInt(tempItemDetails[2]));
				} else {
					newItem = new RowOfCandy(tempItemDetails[1],
							Integer.parseInt(tempItemDetails[2])); 
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
	
	public Map<String, VendItem> getStockDetails() {
		Map<String, VendItem> temp = new HashMap<String, VendItem>();
		temp.putAll(machineProducts);
		return temp;
	}
	
}