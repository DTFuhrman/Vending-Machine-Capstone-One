package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfCandyTest {

	RowOfCandy candyTest;

	public void testMethod(String name, int price, String expected, String testing) {
		candyTest = new RowOfCandy(name, price);
		Assert.assertEquals(expected, testing);
	}
	
	@Test 
	public void this_will_test_for_amount_of_candy() {
		testMethod("Snickers", 175, "175", Integer.toString(candyTest.getNumberAvailable()));
	}

	@Test 
	public void this_will_test_candy_sound() {
		testMethod("Snickers", 175 , "Munch Munch, Yum!" , candyTest.getDispenseAlert());
	}
	
	@Test 
	public void this_will_test_for_candy_price() {
		testMethod("Snickers", 175 , "175" , Integer.toString(candyTest.getPriceInCents()));
	}
	@Test
	public void this_will_test_decrimented_number() {
		RowOfCandy candyTest = new RowOfCandy ("name", 0);
		candyTest.decrimentNumber();
			
	}
}

