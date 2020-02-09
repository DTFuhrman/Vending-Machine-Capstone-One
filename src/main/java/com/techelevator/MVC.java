package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MVC {

	
	//our control flow works because of a system of launchers in our controller class
	//When a controller object is instantiated it instantiates a data object 
	//And a Interface object. This means the data and the UI are accessible to
	//the functionality without being as dependent. We tried to use good MVC design
	//to keep it loosely coupled and organized.
	public static void main(String[] args) {

		Machine main = new Machine("vendingmachine.csv");
		while(true) {
		main.launcher();
		}
	}
	
	
}