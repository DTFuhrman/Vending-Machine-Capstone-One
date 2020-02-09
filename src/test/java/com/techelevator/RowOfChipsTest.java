package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfChipsTest {

	RowOfChips chipTest;

//	public void testMethod(String name, int price, String expected, String testing) {
//		chipTest = new RowOfChips(name, price);
//		Assert.assertEquals(expected, testing);     
//	}

	@Test 
	public void this_will_test_for_amount_of_chips() {
		RowOfChips chipTest = new RowOfChips("lays", 3);
		Assert.assertEquals(5, chipTest.getNumberAvailable());
//		testMethod("Salt n Vinegar" , 200 , "200" , Integer.toString(chipTest.getNumberAvailable()));
	}
	
	@Test 
	public void this_will_test_for_chip_sound() {
		RowOfChips chipTest = new RowOfChips ("Crunch Crunch, Yum!", 5);
		Assert.assertEquals("Crunch Crunch, Yum!", chipTest.getDispenseAlert());
//		testMethod("Salt n Vinegr", 200 , "Crunch Crunch, Yum" , chipTest.getDispenseAlert());
	}
	
	@Test 
	public void this_will_test_for_chip_price() {
		RowOfChips chipTest = new RowOfChips ("lays", 150);
		Assert.assertEquals(150, chipTest.getPriceInCents());
//		testMethod ("Salt n Vinegar", 200 , "200" , Integer.toString(chipTest.getPriceInCents()));
	}
	

}

