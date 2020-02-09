package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfChipsTest {

	RowOfChips chipTest;

	public void testMethod(String name, int price, String expected, String testing) {
		chipTest = new RowOfChips(name, price);
		Assert.assertEquals(expected, testing);     
	}

	@Test 
	public void this_will_test_for_amount_of_chips() {
		testMethod("Salt n Vinegar" , 200 , "200" , Integer.toString(chipTest.getNumberAvailable()));
	}
	
	@Test 
	public void this_will_test_for_chip_sound() {
		testMethod("Salt n Vinegr", 200 , "Crunch Crunch, Yum" , chipTest.getDispenseAlert());
	}
	
	@Test 
	public void this_will_test_for_chip_price() {
		testMethod ("Salt n Vinegar", 200 , "200" , Integer.toString(chipTest.getPriceInCents()));
	}
	
	@Test
	public void this_will_test_decrimented_number() {
		RowOfChips chipTest = new RowOfChips("name", 0);
		chipTest.decrimentNumber();
			
	}
}

