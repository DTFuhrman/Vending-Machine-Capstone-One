package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfBeverageTest {

	RowOfBeverage testBev;

	public void testMethod(String name, int price, String expected, String testing) {
		testBev = new RowOfBeverage(name, price);
		Assert.assertEquals(expected, testing);
	}

	@Test
	public void this_will_test_amount_of_beverages() {
		RowOfBeverage bevTest = new RowOfBeverage("name", 0);
		bevTest.getNumberAvailable();
//		testBev.getNumberAvailable();
//		testMethod("Coke", 125, "5", Integer.toString(testBev.getNumberAvailable()));
	}

	@Test
	public void this_will_test_for_beverage_sound() {
		RowOfBeverage bevTest = new RowOfBeverage("name", 0);
		bevTest.getDispenseAlert();
//		testBev.getDispenseAlert();
//		testMethod("Coke", 125, "Glug Glug, Yum!", testBev.getDispenseAlert());

	}

	@Test
	public void this_will_test_for_beverage_price() {
		RowOfBeverage bevTest = new RowOfBeverage("name", 0);
		bevTest.getPriceInCents();
//		testBev.getPriceInCents();
//		testMethod("Coke", 125, "125", Integer.toString(testBev.getPriceInCents()));
	}
	
	@Test
	public void this_will_test_decrimented_number() {
		RowOfBeverage bevTest = new RowOfBeverage("name", 0);
		bevTest.decrimentNumber();
			
	}
}
