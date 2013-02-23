package com.megginson.sloop;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(com.megginson.sloop.model.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
