package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfBeverageTest {

	RowOfBeverage testBev;

//	public void testMethod(String name, int price, String expected, String testing) {
//		testBev = new RowOfBeverage(name, price);
//		Assert.assertEquals(expected, testing);
//	}

	@Test
	public void this_will_test_amount_of_beverages() {
		RowOfBeverage testBev = new RowOfBeverage ("Coke" , 2);
		Assert.assertEquals(5, testBev.getNumberAvailable());
	}

//		testMethod("Coke", 125, "5", Integer.toString(testBev.getNumberAvailable()));
//	}

	@Test
	public void this_will_test_for_beverage_sound() {
		RowOfBeverage testBev = new RowOfBeverage ("glug glug, yum" , 0);
		Assert.assertEquals("Glug Glug, Yum!", testBev.getDispenseAlert());
		
//		testMethod("Coke", 125, "Glug Glug, Yum!", testBev.getDispenseAlert());

	}

	@Test
	public void this_will_test_for_beverage_price() {
		RowOfBeverage testBev = new RowOfBeverage("name", 200);
		Assert.assertEquals(200, testBev.getPriceInCents());
		
//		testMethod("Coke", 125, "125", Integer.toString(testBev.getPriceInCents()));
	}
	

}
