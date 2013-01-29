package com.megginson.sloop.test.io;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DataCollectionIOTest.class);
		//$JUnit-END$
		return suite;
	}

}
