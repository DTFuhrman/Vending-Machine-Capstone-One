package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfCandyTest {

	RowOfCandy candyTest;

//	public void testMethod(String name, int price, String expected, String testing) {
//		candyTest = new RowOfCandy(name, price);
//		Assert.assertEquals(expected, testing);
//	}
	
	@Test 
	public void this_will_test_for_amount_of_candy() {
		RowOfCandy candyTest = new RowOfCandy ("Twix" , 23);
		Assert.assertEquals(5, candyTest.getNumberAvailable());
//		testMethod("Snickers", 175, "175", Integer.toString(candyTest.getNumberAvailable()));
	}

	@Test 
	public void this_will_test_candy_sound() {
		RowOfCandy candyTest = new RowOfCandy("Munch Munch, Yum!", 0);
		Assert.assertEquals("Munch Munch, Yum!", candyTest.getDispenseAlert());
//		testMethod("Snickers", 175 , "Munch Munch, Yum!" , candyTest.getDispenseAlert());
	}
	
	@Test 
	public void this_will_test_for_candy_price() {
		RowOfCandy candyTest = new RowOfCandy ("Twix" , 75);
		Assert.assertEquals(75, candyTest.getPriceInCents());
//		testMethod("Snickers", 175 , "175" , Integer.toString(candyTest.getPriceInCents()));
	}
	
}

