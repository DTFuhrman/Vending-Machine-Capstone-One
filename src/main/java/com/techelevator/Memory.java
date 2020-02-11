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

// Memory
	// This class holds almost all of the real data objects in our program.
	// including the stock objects and the list of the stock, and the text
	// to display in the menues and everything.
	// In retrospect it might have made sense to seperate this out a bit more
	// into a File reading and writing class and a data class.

//	CONSTRUCTOR
	// Our constructor takes a path to pull the inventory
	// it still needs to be hard coded at some point, but
	// it makes it more extensible I think because it could be
	// programmed to launch from a terminal with a path
	// so you could access different stocks.
	public Memory(String path) {
		// STOCK *** This initializes our stock when it is first instantiated
		// It makes sure the stock file exists, instantiates objects for each row
		// and sets the number available to five
		this.initialStockPath = path;
		this.initialStockFile = getFile(path);
		this.currentStockList = (initializeStock(initialStockFile));
		// POTENTIAL EXTENSIBIITY: We could have persistent stock memory
		// if we made functions similar to our log writing functions

		// SECURITY LOG STUFF *** This instantiates our log file
		// it automatically checks if one exists and creates one if it doesn't
		//
		this.logPath = "log.txt";
		this.logFile = newFile(logPath);

		// SALES ***
		// POTENTIAL EXTENSIBILITY: We have not yet implemented the Sales log function,
		// but we have some ideas for it.
		this.salesPath = "SalesReport.txt";
		this.salesReport = newFile(salesPath);
		initializeSalesReport();

		// BALANCE and CURRENT SELECTION This Initializes the current balance to zero
		// and the current selection to A1 to avoid any null pointint if something
		// breaks.
		this.currentBalance = 000;
		this.currentSelection = "A1";
		// This appends a log entry to our log file every time the machine instantiates
		logStartup();
	}

	//// MENUS **********
	//// These arrays contain the text for the menu printer
	//// This uses less memory than a list and loads faster (I think)
	//// We made them final because they will never change during runtime.
	//// The list printer doesn't display menu selections that start with h.
//		WELCOME SCREEN - This welcome screen boxes in the functionality.
	// This way when you quit the menus it doesn't turn all the way off.
	// You can quit from this screen if you enter q or Q or quit oor something.
	// EXTENSIBILITY: This menu could also allow you to enter a secret admin mode
	// to display the hidden menus and give you permission to enter the report
	// menus.
	private final String[] WELCOME_SCREEN = new String[] { "[VENDOMATIC-8000]$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$                            $$",
			"$$         Welcome to         $$", "$$       VendoMatic-8000      $$", "$$        Press (enter)       $$",
			"$$    to start your journey   $$", "$$                            $$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$", "$$                            $$", "$$    awaiting user input     $$",
			"$$                            $$", "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",
			"$$$$$$$$$$$$$$$$$[TECH$ELEVATOR]", };
//		Main menu - this is our main menu.
	// POTENTIAL EXTENSIBILITY: We added hidden menus for menus that could deal with
	// extra extensibility in the future.
	private final String[] MAIN_MENU = new String[] { "h(0) Security Log Menu", "(1)  Display Vending Machine Items",
			"(2)  Enter Purchase Menu", "(3)  Exit", "h(4)  Sales Report Menu", "h(5)  Order Report Menu" };
//		Purchase Menu - This is our purchase menu.
	private final String[] PURCHASE_MENU = new String[] { "h(0)", "(1)  Feed Money", "(2)  Select Item",
			"(3)  Finish Transaction" };
//		Log Menu - POTENTIAL EXTENSIBILITY: This menu could be implemented to give admins access to extra functions 
	private final String[] LOG_MENU = new String[] { "h(0)", "(1)  View Security Log", "(2) Back to Main Menu" };
//		Sales Report Menu - POTENTIAL EXTENSIBILITY: This menu could be implemented to give admins access to extra functions
	private final String[] REPORT_MENU = new String[] { "h(0)", "(1)  Write new Report File", "(2)  View Report",
			"(3)  Back To Main Menu" };
//		Vendor Order Menu - POTENTIAL EXTENSIBILITY: This menu could be implemented to give admins access to extra functions
	private final String[] ORDER_MENU = new String[] { "h(0)", "UPCOMING FUNCIONTALITY!",
			"(any key)  Back to Main Menu" };
//		Array of menus - We store each of our menus in a private array for our printer to access them easily.
	private final String[][] menus = new String[][] { WELCOME_SCREEN, MAIN_MENU, PURCHASE_MENU, LOG_MENU, REPORT_MENU,
			ORDER_MENU };

	//// Stock *********** This is where we store our stock in our map
	// It's also where we keep our stock file path and our stock file object
	private String initialStockPath;
	private File initialStockFile;
//		Current Stock		/ Get / 
	private Map<String, VendItem> currentStockList;
//		Current Stock File 	 - POTENTIAL EXTENSIBILITY: If we wanted to make a persistant stock
	// we would implement that here
//	private String currentStockPath;
//	private File currentStockFile;

	// [POTENTIAL EXTENSIBILITY!] We were thinking it would
	// not be too hard to print
	// our current inventory to a file to create persistent
	// memory instead of initializing to the same state
	// every time it's turned on.

	//// Log ***************************** LOG
//		This is our log path and log file
	private String logPath;
	private File logFile;

	//// Sales Report ***************************** Sales
//		 - POTENTIAL EXTENSIBILITY: If we were to implement a
	// sales report functionality we would start by
	// making a list to store our sales in the right format
	// we already store deposits as cents in integer form,
	// but we would also need to track the date and time and
	// some other stuff. This would be our file and our path
	// We would probably autogenerate a new path with the date
	// and time each time the machine turned on.
	private List<String> salesList = new ArrayList<String>();
	private List<Integer> deposits = new ArrayList<Integer>();
	private String salesPath;
	private File salesReport;

	//// MONEY AND CURRENT STATE STUFF
	// this is our current balance when money has been fed but not
	// spent or returned as change yet
	// we store all money as cents in integer form and reformat it for display to
	//// the user
	// This avoids rounding errors UNLESS DIVISION BECOMES NECESSARY
	// We would have to work around this if sales became a thing
	// The trade off is also that we can't have really big numbers,
	// but if we are ever dealing with 2.1 billion cents it's probably already a
	//// problem.
	private int currentBalance;
	// We store our current selection here, so we can reference it when we need to
	// look
	// at what has been selected.
	private String currentSelection;

//// METHODS!!! ******************************

//	 GETTERS/SETTERS *********************
	// All of these methods are to access or alter data
	// all of our data is private, so these functions are necessary
	// this also stop unexpected behavior like the ability to change
	// current balance without using the feed bill method

	// this menu decreases the amount of code necessary to call a menu
	// and makes them accessible despite being private
	public String[] getMenu(int menuIndex) {
		return menus[menuIndex];
	}

	// normal getter for curent balance
	public int getCurrentBalance() {
		return this.currentBalance;
	}

	// This changes the current baance, but you can't set it like a normal setter
	// You can only add to it or subtract from it with negative numbers It's only
	// accessible by feeding bills, making purchases, or getting change.
	// I made it protected because it seems sensitive
	protected void setCurrentBalance(int amount) {
		this.currentBalance += amount;
	}

	// This function tracks all of the money that is spent in the machine
	// by storing the income from each purchase in our deposit list
	public void addDeposit(int amount) {
		deposits.add(amount);
	}

	// This returns a boolean reflecting whether we are out of an item or not
	public boolean checkStock(String key) {
		boolean hasItem = false;
		if (currentStockList.get(key).getNumberAvailable() > 0) {
			hasItem = true;
		}
		return hasItem;
	}

	// This gets the price of an item if you have it's key
	public int getPrice(String key) {
		int price = currentStockList.get(key).getPriceInCents();
		return price;
	}

	// This decreases the stock by one when an item is purchased
	public void decrimentStock(String key) {
		currentStockList.get(key).decrimentNumber();
	}

//	TO STRINGS
	// This returns the current balance as a string
	// In retrospect it would have been more useful if we made
	// a more general function to convert ints representing
	// cents into display strings
	public String getCurrentBalanceAsString() {
		return "$" + Integer.toString(this.currentBalance / 100) + "." + Integer.toString(this.currentBalance % 100);
	}

	// This changes the current selection for purchase
	public void setCurrentSelection(String input) {
		this.currentSelection = input;
	}

	// This gets the current key selected in the selection menu
	public String getCurrentSelection() {
		return this.currentSelection;
	}

	// ************ HELPER METHODS ************

	// STOCK HELPERS!!!! *****************

	// This helper method initializes all the stock from the text file as objects
	// it is called in the constructor, so every time it is made in memory, the
	// stock will resent
	// This COULD be altered to use a persistant stock from a txt file, or to call
	// different stocks
	// depending on an initial selection.
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

	// This method creates a copy of the map so we can access it elsewhere,
	// but it doesn't give anyone access to altering the stock outside the setters
	// above
	// because it's a copy (I think)
	//
	public Map<String, VendItem> getStockDetails() {
		Map<String, VendItem> temp = new TreeMap<String, VendItem>();
		temp.putAll(currentStockList);
		return temp;
	}

	// FILE I/O HELPERS **********
	// this method validates a file path and ensures it exists before returning the
	// file object
	// This COULD write to the log file if it fails and shut the machine off
	// Because we validate the file in this method, we won't get exceptions
	// elsewhere.
	// So we know where to look if one happens

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

	// Similar to above, this file ensures a file exists, but if it doesn't this
	// method
	// creates a new one. It will never overwrite, because it checks to see if it
	// exists first.
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

	//// ************* LOG METHODS ***********

	// This function creates a date formatted for use in the log
	private String getDateForLog() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm:ss a ");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		return dateTime;
	}

	// This function simply appends to the log file
	public void appendToLog(String input) {
		try (FileWriter logWriter = new FileWriter(logFile, true)) {

			logWriter.append(input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/// LOG METHODS - Each of these methods formats and prints a different kind of
	/// message to the log
	// they are used by the machines functions that are performing actual functions
	/// so that the
	// log entry is recorded when the behavior being logged occurs.
	public void logStartup() {
		// this logs when the machine is instantiated
		appendToLog(">" + getDateForLog() + "POWER ON: VendoMatic8000 on");
	}

	public void logShutDown() {
		// This logs when the machine shuts down if you quit properly
		// (unless you just kill the processes with the red button)
		appendToLog(">" + getDateForLog() + "POWER OFF: VendoMatic8000 off");
	}

	public void logFeed(int fed, int current) {
		// This logs every time money is fed into the machine
		appendToLog(">" + getDateForLog() + "FEED MONEY: $" + fed / 100 + "." + fed % 100 + " $" + current / 100 + "."
				+ current % 100 + " ");
	}

	public void logFeedFailure(int fed, int current) {
		// This logs every time someone attempts to feed money incorrectly
		appendToLog(">" + getDateForLog() + "FEED MONEY: failed to feed $" + fed / 100 + "." + fed % 100 + " $"
				+ current / 100 + "." + current % 100 + " ");
	}

	// This logs every time a purchase is successful
	// We COULD implement a log function to record purchases
	// that are unsuccessful as well and log the reason why
	// sold out/insufficient funds/invalid selection etc.
	public void logPurchase(String slot, String name, int balanceBeforeSale, int afterSale) {
		appendToLog(">" + getDateForLog() + "SALE: " + name + " " + slot + balanceBeforeSale / 100 + "."
				+ balanceBeforeSale % 100 + " $" + afterSale / 100 + "." + afterSale % 100 + " ");
	}

	// This logs every time change is given. we COULD record which coins were given
	// too.
	public void logChange(int changeGiven) {
		appendToLog(">" + getDateForLog() + "GIVE CHANGE: $" + changeGiven / 100 + "." + changeGiven % 100 + " "
				+ this.currentBalance + " ");
	}

	///// SALES REPORT FUNCTIONS!!!! ***********

	// We were originally going to create a new sales report each time the machine
	// shut off, but that doesn't add up to the example output, which seems
	// to have one ongoing report with mulitple runtimes.
	// we can tell because the sales are often greater than 5.
	// so instead we will make one ongoing report
//			 This function creates a date formatted for use in the sales report file name
	private String getDateForReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd-HH-mm-a-");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		return dateTime;
	}
//
//	private String getTimeStampForReport() {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm a >>>> ");
//		LocalDateTime now = LocalDateTime.now();
//		String dateTime = dtf.format(now);
//		return dateTime;
//	}	

	// ******************** TRYING TO IMPLEMENT SALES REPORT
	// ****************************
	//

	// This method will make sure our sales report is not empty
	public void initializeSalesReport() {
		try (Scanner initialReportScanner = new Scanner(salesReport)) {
			// If file is empty initialize it with 0 sales for each item
			// then you can proceed as normal
			if (!initialReportScanner.hasNextLine()) {
				for (Map.Entry<String, VendItem> entry : currentStockList.entrySet()) {
					String name = entry.getValue().getName();
					writeToSalesReport(name + "|0\n");

				}
				String startingTotal = "\n\n\t\tTotal: $00.00";
				writeToSalesReport(startingTotal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void incrementSalesReport(String itemName) {
		try (Scanner reportScanner = new Scanner(salesReport)) {
		// Read the file into the list
		while (reportScanner.hasNextLine()) {
			String line = reportScanner.nextLine();
			salesList.add(line);
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Find the line that contains your search term
		// grab the number and parse it
		// add the new sale by incrementing
		// write the new line
		for (int i = 0; i < salesList.size(); i++) {
			if (salesList.get(i).contains(itemName)) {
				String[] parts = salesList.get(i).split("\\|");
				int salesToDate = Integer.parseInt(parts[1]);
				String newLine = parts[0] + "|" + salesToDate + 1 + "\n";
				salesList.set(i, newLine);
				break;
			}
		}
		// read the total from the bottom
		String previousTotalString = salesList.get(salesList.size() - 1);
		previousTotalString = previousTotalString.replaceAll("[^0-9]", "");
		int previousTotal = Integer.parseInt(previousTotalString);
		
		// empty the file
		salesReport.delete();
		
		// by overwriting it with a title and date and time stamp
		salesReport = newFile(salesPath);
		
//		try (FileWriter overWriteWithNewHeading = new FileWriter(salesReport, false)) {
//			overWriteWithNewHeading.append("New Sales Report Generated: " + getDateForReport() + "\n\n\n");
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		// rewrite the file line by line
		for (String str : salesList) {
			writeToSalesReport(str + "\n");
		}
		
		// record the total revenue
		int newPennies = 0;
		newPennies += previousTotal;
		for (int deposit : deposits) {
			newPennies += deposit;
		}
		//empty the deposit list
		deposits.removeAll(deposits);

		//Write the new sales list to the file
		for(String entry: salesList) {
			writeToSalesReport(entry);
		}
		
		String newTotal = "\n\n\t\tTotal: $" + newPennies/100 + "." + newPennies%100;
		writeToSalesReport(newTotal);
		
		//empty the sales list
		salesList.removeAll(salesList);
		
	}

	// this helper method will write to the file
	public void writeToSalesReport(String input) {

		try (FileWriter reportWriter = new FileWriter(salesReport, true)) {

			reportWriter.append(input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	
//
//	public void logSale(String name, int totalSales) {
//		String reportSale = name
//	}
//
//	public void reportIncome() {
//		int sum = 0;
//		for (int pennies : deposits) {
//			sum += pennies;
//		}
//		String runtimeIncome = "\n\n\t\tTotal: $" + sum / 100 + "." + sum % 100 + "0";
//		writeToSalesReport(runtimeIncome);
//	}

}
