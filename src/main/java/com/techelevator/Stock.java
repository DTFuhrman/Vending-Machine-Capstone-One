package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Stock {

	private File baseStock;
	private List<VendItem> machineProducts;

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
		machineProducts = new ArrayList<VendItem>();
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] tempItemDetails = line.split("|");
				VendItem newItem;
				if (tempItemDetails[3].toUpperCase().contains("BEVERAGE")) {
					newItem = new Beverages(tempItemDetails[1], tempItemDetails[0],
							Integer.parseInt(tempItemDetails[2]));
				} else if (tempItemDetails[3].toUpperCase().contains("CHIP")) {
					newItem = new Chips(tempItemDetails[1], tempItemDetails[0],
							Integer.parseInt(tempItemDetails[2]));
				} else if (tempItemDetails[3].toUpperCase().contains("GUM")) {
					newItem = new Gum(tempItemDetails[1], tempItemDetails[0],
							Integer.parseInt(tempItemDetails[2]));
				} else {
					newItem = new Candy(tempItemDetails[1], tempItemDetails[0],
							Integer.parseInt(tempItemDetails[2])); 
					}
				//make it five instead
				machineProducts.add(newItem);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update stock if an item is sold
	public void removeFromStock() {
		
	}
	
	//display stock if menu display is selected
	public void displayStock() {
		
	}
	
}

//		File inputFile = getInputFileFromUser();
//		try(Scanner fileScanner = new Scanner(inputFile)) {
//			while(fileScanner.hasNextLine()) {
//				String line = fileScanner.nextLine();
//				String rtn = line.substring(0, 9);
//				
//				if(checksumIsValid(rtn) == false) {
//					System.out.println(line);
//				}
//			}
//		}

		// read new stock file / current stock file

		// create current stock file

		// update current stock file

		// delete machine stock
