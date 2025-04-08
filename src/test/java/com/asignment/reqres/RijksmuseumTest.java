package com.asignment.reqres;

import org.testng.annotations.Test;

import com.assignment.reqresutility.DefaultDriverManager;

import org.testng.annotations.BeforeTest;


/**
 * @author
 *
 */
public class RijksmuseumTest {

	static RijksmuseumPOJO rijkpojo;

	@BeforeTest
	public void setup() throws Exception {

		DefaultDriverManager.loadConfig();
		rijkpojo = new RijksmuseumPOJO();
	}

	@Test
	public static void test() throws Exception {
		new RijksmuseumController().rijksmuseumController(rijkpojo);
		new RijksmuseumComparator().compareRijkAttributes(rijkpojo);

	}

}
