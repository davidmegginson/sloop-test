package com.megginson.sloop.test.util;

import java.util.Arrays;

import junit.framework.TestCase;

import com.megginson.sloop.util.FilteredList;
import com.megginson.sloop.util.ListItemFilter;

public class FilteredListTest extends TestCase {
	
	private final static String ITEMS[] = { "A", "B", "B", "C" };

	private FilteredList<String> mFilteredList;

	protected void setUp() throws Exception {
		super.setUp();
		ListItemFilter<String> filter = new ListItemFilter<String>() {
			@Override
			public boolean isMatch(String item) {
				return "B".equals(item);
			}
		};
		mFilteredList = new FilteredList<String>(filter, Arrays.asList(ITEMS));
	}

	public void testSize() {
		assertEquals(2, mFilteredList.size());
	}

	public void testGet() {
		assertEquals("B", mFilteredList.get(0));
	}

}
