package com.megginson.sloop.model;

import com.megginson.sloop.model.impl.DataCollectionImplTest;
import com.megginson.sloop.model.impl.DataEntryImplTest;
import com.megginson.sloop.model.impl.DataRecordImplTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DataCollectionImplTest.class);
		suite.addTestSuite(DataEntryImplTest.class);
		suite.addTestSuite(DataRecordImplTest.class);
		suite.addTestSuite(BookmarkTest.class);
		//$JUnit-END$
		return suite;
	}

}
