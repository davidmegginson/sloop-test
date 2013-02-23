package com.megginson.sloop.model;

import com.megginson.sloop.model.impl.DataCollectionTest;
import com.megginson.sloop.model.impl.DataEntryTest;
import com.megginson.sloop.model.impl.DataRecordTest;

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
