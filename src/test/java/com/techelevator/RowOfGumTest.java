package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class RowOfGumTest {
	
	RowOfGum gumTest;
	
//	public void testMethod(String name, int price, String expected, String testing) {
//		gumTest = new RowOfGum(name, price);
//		Assert.assertEquals(expected, testing);
//	}
//	
	@Test 
	public void this_will_test_for_amount_of_gum() {
		RowOfGum gumTest = new RowOfGum("name", 0);
		gumTest.getNumberAvailable();
		Assert.assertEquals("name", 5);
//		testMethod("BubbleYum", 75, "75", Integer.toString(gumTest.getNumberAvailable()));
	}
 
	@Test
	public void this_will_test_for_gum_sound() {
		RowOfGum gumTest = new RowOfGum("name", 0);
		gumTest.getDispenseAlert();
//	testMethod("BubbleYum", 75, "Chew Chew, Yum", gumTest.getDispenseAlert());
	}
	
	@Test 
	public void this_will_test_price_for_gum() {
		RowOfGum gumTest = new RowOfGum("name", 0);
		gumTest.getPriceInCents();
//		testMethod("BubbleYum" , 75, "75" , Integer.toString(gumTest.getPriceInCents()));
	}
	@Test
	public void this_will_test_decrimented_number() {
		RowOfGum gumTest = new RowOfGum("name", 0);
		gumTest.decrimentNumber();
			
	}
}
