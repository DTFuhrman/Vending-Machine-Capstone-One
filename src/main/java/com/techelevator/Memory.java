package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Memory {

//	Constructor

	public Memory(String path) {
		// STOCK ***
		this.initialStockPath = path;
		this.initialStockFile = getFile(path);
		// set currentStockList
		this.currentStockList = (initializeStock(initialStockFile));
//We could extend this to have persistent stock memory if we made functions similar to our log writing unctions
		// this.currentStockPath = getDateForFileNames() + "CurrentStock.txt";

		// SECURITY LOG ***
		this.logPath = "log.txt";
		this.logFile = newFile(logPath);

		// SALES ***
//		this.salesPath = "sales-for-" + getDateForFileNames() + "report.txt";
//		this.salesReport = newFile(initialStockPath);

		// Balance and Deposits
		this.currentBalance = 0;
		this.currentSelection = "A1";
		logStartup();
	}

	//// Menus **** List<object>
//		Front Menu
	private final String[] WELCOME_SCREEN = new String[] { "[VENDOMATIC-8000]$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$                            $$",
			"$$         Welcome to         $$", "$$       VendoMatic-8000      $$", "$$        Press (enter)       $$",
			"$$    to start your journey   $$", "$$                            $$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$                            $$", "$$    awaiting user input     $$",
			"$$                            $$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$[TECH$ELEVATOR]", };
//		Main menu
	private final String[] MAIN_MENU = new String[] { "h(0) Security Log Menu", "(1)  Display Vending Machine Items",
			"(2)  Enter Purchase Menu", "(3)  Exit", "h(4)  Sales Report Menu", "h(5)  Order Report Menu" };
//		Purchase Menu
	private final String[] PURCHASE_MENU = new String[] { "h(0)", "(1)  Feed Money", "(2)  Select Item",
			"(3)  Finish Transaction" };
//		Log Menu
	private final String[] LOG_MENU = new String[] { "h(0)", "(1)  View Security Log", "(2) Back to Main Menu" };
//		Sales Report Menu
	private final String[] REPORT_MENU = new String[] { "h(0)", "(1)  Write new Report File", "(2)  View Report",
			"(3)  Back To Main Menu" };
//		Vendor Order Menu
	private final String[] ORDER_MENU = new String[] { "h(0)", "UPCOMING FUNCIONTALITY!",
			"(any key)  Back to Main Menu" };
//		array of menus
	private final String[][] menus = new String[][] { WELCOME_SCREEN, MAIN_MENU, PURCHASE_MENU, LOG_MENU, REPORT_MENU,
			ORDER_MENU };

	//// Stock **** Files ***OR*** Maps <key:String, value:VendItem>
//		Initial Stock File	/ load
	private String initialStockPath;
	private File initialStockFile;
//		Current Stock		/ Get / 
	protected Map<String, VendItem> currentStockList;
//		Current Stock File 	/(print to/write file)
//	private String currentStockPath;
//	private File currentStockFile;

	//// Log ***************************** LOG
//		current log being written; FILE
	private String logPath;
	private File logFile;

	//// Sales Report ***************************** Sales
//		current report being written; FILE
	private List<String> salesList = new ArrayList<String>();
	protected List<Integer> deposits = new ArrayList<Integer>();
	private String salesPath;
	private File salesReport;
//		most recent log

	//// Money ***************************** Current State STUFF
//	Income Bank  		/ Get(Sale report)
//  current balance
	private int currentBalance;
	private String currentSelection;

//	//// Scanners *************************************** SCANNERS
////		initial stock reader
//	public Scanner initialStockReader; // = new Scanner(initialStockPath);
////		current stock reader
//	public PrintWriter initialStockPrinter; // = new PrintWriter(initialStockPath);
////		current stock reader
//	public Scanner currentStockReader; // = new Scanner(currentStockPath);
////		current stock printer
//	public PrintWriter currentStockPrinter; // = new PrintWriter(currentStockPath);
////		current stock file writer
//	public FileWriter currentStockFileWriter; // = new FileWriter(currentStockPath);
////		log reader
//	public Scanner logReader; // = new Scanner(currentLogPath);
////		log printer
//	public PrintWriter logPrinter; // = new PrintWriter(currentLogPath);
////		log file writer
//	public FileWriter logFileWriter; // = new FileWriter(currentLogPath);

//	Helper METHODS***********************

//	 GETTERS/SETTERS *********************

	public String[] getMenu(int menuIndex) {
		return menus[menuIndex];
	}

	public int getCurrentBalance() {
		return this.currentBalance;
	}

	protected void setCurrentBalance(int amount) {
		this.currentBalance += amount;
	}

	public void addDeposit(int amount) {
		deposits.add(amount);
	}

	public boolean checkStock(String key) {
		boolean hasItem = false;
		if (currentStockList.get(key).getNumberAvailable() > 0) {
			hasItem = true;
		}
		return hasItem;
	}

	public int getPrice(String key) {
		int price = currentStockList.get(key).getPriceInCents();
		return price;
	}

	public void decrimentStock(String key) {
		currentStockList.get(key).decrimentNumber();
	}

//	TO STRINGS
	public String getCurrentBalanceAsString() {
		return "$" + Integer.toString(this.currentBalance / 100) + "." + Integer.toString(this.currentBalance % 100);
	}

	public void setCurrentSelection(String input) {
		this.currentSelection = input;
	}

	public String getCurrentSelection() {
		return this.currentSelection;
	}

	// fixme fixme fixme fixme fixme fixme fixme fixme fixme fixme fixme
	// this will track the log of purchases
	// what was purchased, the date, and the time, also what bill was used for
	// purchase.
	private String getDateForLog() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm:ss a ");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		return dateTime;
	}

	//// LOG WRITING AND READING WILL GO HERE
	// this will print input into a log list
	public void appendToLog(String input) {
		try (FileWriter logWriter = new FileWriter(logFile, true)) {
			
			logWriter.append(input);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	///LOG METHODS
	public void logStartup() {
		// newest entry in last
		appendToLog(">" + getDateForLog() + "POWER ON: VendoMatic8000 on");
	}

	public void logShutDown() {
		// newest entry in last
		appendToLog(">" + getDateForLog() + "POWER OFF: VendoMatic8000 off");
	}

	public void logFeed(int fed, int current) {
		// newest entry in last
		appendToLog(">" + getDateForLog() + "FEED MONEY: $" + fed/100 + "." + fed%100 + " $" 
				+ current/100 + "." + current%100 + " ");
	}
	
	public void logFeedFailure(int fed, int current) {
		// newest entry in last
		appendToLog(">" + getDateForLog() + "FEED MONEY: failed to feed $" + fed/100 + "." + fed%100 + " $" 
				+ current/100 + "." + current%100 + " ");
	}
	
	public void logPurchase(String slot, String name, int balanceBeforeSale, int afterSale) {
		appendToLog(">" + getDateForLog() + "SALE: " + name + " " + slot + balanceBeforeSale/100 
				+ "." + balanceBeforeSale%100 + " $" + afterSale/100 + "." + afterSale%100 + " ");
	}
	
	public void logChange(int changeGiven) {
		appendToLog(">" + getDateForLog() + "GIVE CHANGE: $" + changeGiven/100 + "." 
				+ changeGiven%100 + " " + this.currentBalance + " ");
	}
	
	
	
//	public File generateSalesReport() {
//		File report = new File(getDateForFileNames() + " - Sales Report");
//
//		return report;
//	}

	/// FROM STOCK!!! *****************************

	// We have a base stock file that represents the main inventory we load from
	// We also have a map that keeps track of the slot location
	// and the row object in that location

	// This constructor will be called by the VendingMachine Class
	// because each machine will have a stock, a cashier, and a UX
	// The constructor fills the stock fully from the original file
	// But it could be updated to track the stock through persistent memory by
	// changing
	// which file it reads from.
//	public Stock(String path) {
//		this.baseStock = getFile(path);
//		getStock(this.baseStock);
//	}

	// this is a helper method to validate a path
	// before we start pulling from it or writing to it
	// right now it ensures the file exists
	// but it can be ALTERED to throw a message if the file exists or expect the
	// file to exist
	public File getFile(String path) {

		File fileToRead = new File(path);

		if (!fileToRead.exists()) { // checks for the existence of a file
			System.out.println(path + "... I can't find that file");
			// call UX to handle this and ask again
			System.exit(1);
		} else if (!fileToRead.isFile()) {// checks to make sure it's not a directory
			System.out.println(path + " is actually not a file");
			// call UX to handle this and ask again
			System.exit(1);
		}

		return fileToRead;
	}

	public File newFile(String path) {

		File fileToRead = new File(path);
		if (!fileToRead.exists()) {
			try {
				fileToRead.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!fileToRead.isFile()) {// checks to make sure it's not a directory
			System.out.println(path + " is actually not a file");
			// call UX to handle this and ask again
			System.exit(1);
		}

		return fileToRead;
	}

	// This helper method fills the machine when it initializes
	// it is only called by the constructor, so it is private
	public Map<String, VendItem> initializeStock(File initialStockFile) {
		Map<String, VendItem> stockList = new TreeMap<String, VendItem>();
		try (Scanner initialStockReader = new Scanner(initialStockFile)) {
			while (initialStockReader.hasNext()) {
				String line = initialStockReader.nextLine();
				String[] tempItemDetails = line.split("\\|");
				String priceString = tempItemDetails[2];
				Double priceDoublePennies = Double.parseDouble(priceString);
				priceDoublePennies *= 100;
				int price = priceDoublePennies.intValue();
				VendItem newItem;
				if (tempItemDetails[3].toUpperCase().contains("DR")) {
					newItem = new RowOfBeverage(tempItemDetails[1], price);
				} else if (tempItemDetails[3].toUpperCase().contains("CH")) {
					newItem = new RowOfChips(tempItemDetails[1], price);
				} else if (tempItemDetails[3].toUpperCase().contains("G")) {
					newItem = new RowOfGum(tempItemDetails[1], price);
				} else {
					newItem = new RowOfCandy(tempItemDetails[1], price);
				}
				stockList.put(tempItemDetails[0], newItem);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stockList;
	}

	// update stock if an item is sold

	// this returns the details so we can display them
	public Map<String, VendItem> getStockDetails() {
		Map<String, VendItem> temp = new TreeMap<String, VendItem>();
		temp.putAll(currentStockList);
		return temp;
	}

}
