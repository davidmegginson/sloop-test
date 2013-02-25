package com.megginson.sloop.model.impl;

import android.annotation.SuppressLint;
import junit.framework.TestCase;

@SuppressLint("DefaultLocale")
public class ContainsStringFilterTest extends TestCase {
	
	private final static String SUBSTRING = "test";
	private final static String VALUE_START = "Test Value";
	private final static String VALUE_MIDDLE = "A Test Value";
	private final static String VALUE_END = "A Value Test";
	private final static String NO_VALUE = "XXX";
	
	private ContainsStringFilter mFilter;
	
	protected void setUp() throws Exception {
		super.setUp();
		mFilter = new ContainsStringFilter(SUBSTRING);
	}
	
	public void testMatch(){
		assertTrue(mFilter.isMatch(SUBSTRING));
		assertTrue(mFilter.isMatch(SUBSTRING.toUpperCase()));
		assertTrue(mFilter.isMatch(SUBSTRING.toLowerCase()));

		assertTrue(mFilter.isMatch(VALUE_START));
		assertTrue(mFilter.isMatch(VALUE_START.toUpperCase()));
		assertTrue(mFilter.isMatch(VALUE_START.toLowerCase()));
		
		assertTrue(mFilter.isMatch(VALUE_MIDDLE));
		assertTrue(mFilter.isMatch(VALUE_MIDDLE.toUpperCase()));
		assertTrue(mFilter.isMatch(VALUE_MIDDLE.toLowerCase()));

		assertTrue(mFilter.isMatch(VALUE_END));
		assertTrue(mFilter.isMatch(VALUE_END.toUpperCase()));
		assertTrue(mFilter.isMatch(VALUE_END.toLowerCase()));
	}
	
	public void testNoMatch(){
		assertFalse(mFilter.isMatch(NO_VALUE));
	}
	
	public void testString(){
		assertEquals("~" + SUBSTRING.toLowerCase(), mFilter.toString().toLowerCase());
	}

}
