package com.megginson.sloop.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(com.megginson.sloop.test.model.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
