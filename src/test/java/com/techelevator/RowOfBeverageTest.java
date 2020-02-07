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
		testMethod("Coke", 125, "5", Integer.toString(testBev.getNumberAvailable()));
	}

	@Test
	public void this_will_test_for_beverage_sound() {
		testMethod("Coke", 125, "Glug Glug, Yum!", testBev.getDispenseAlert());

	}

	@Test
	public void this_will_test_for_beverage_price() {
		testMethod("Coke", 125, "125", Integer.toString(testBev.getPriceInCents()));
	}
	
	@Test
	public void this_will_test_decrimented_number() {
		RowOfBeverage bevTest = new RowOfBeverage("name", 0);
		bevTest.decrimentNumber();
			
	}
}
