package com.megginson.sloop.model.impl;

import android.annotation.SuppressLint;
import junit.framework.TestCase;

@SuppressLint("DefaultLocale")
public class EqualsStringFilterTest extends TestCase {

	private final static String VALUE = "Test Value";

	private EqualsStringFilter mFilter;

	protected void setUp() throws Exception {
		super.setUp();
		mFilter = new EqualsStringFilter(VALUE);
	}

	public void testMatch() {
		assertTrue(mFilter.isMatch(VALUE));
		assertTrue(mFilter.isMatch(VALUE.toUpperCase()));
		assertTrue(mFilter.isMatch(VALUE.toLowerCase()));
	}

	public void testNoMatch() {
		assertFalse(mFilter.isMatch(VALUE + VALUE));
	}

	public void testString() {
		assertEquals("=" + VALUE.toLowerCase(), mFilter.toString()
				.toLowerCase());
	}

}
