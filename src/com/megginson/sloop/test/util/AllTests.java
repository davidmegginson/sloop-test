package com.megginson.sloop.test.util;

import com.megginson.sloop.test.util.FilteredListTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(FilteredListTest.class);
		//$JUnit-END$
		return suite;
	}

}
