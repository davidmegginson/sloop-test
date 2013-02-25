package com.megginson.sloop.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTest(com.megginson.sloop.model.impl.AllTests.suite());
		suite.addTestSuite(BookmarkTest.class);
		suite.addTestSuite(CollectionFilterTest.class);
		// $JUnit-END$
		return suite;
	}

}
