package com.megginson.sloop.test.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DataCollectionTest.class);
		suite.addTestSuite(DataEntryTest.class);
		suite.addTestSuite(DataRecordTest.class);
		suite.addTestSuite(BookmarkTest.class);
		//$JUnit-END$
		return suite;
	}

}
