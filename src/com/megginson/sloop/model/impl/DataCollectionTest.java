package com.megginson.sloop.model.impl;

import junit.framework.TestCase;

import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.ValueFilter;

public class DataCollectionTest extends TestCase {

	private final static String HEADERS[] = { "A", "B", "C", };
	
	private final static String VALUES[][] = {
		{ "A1", "B", "C1" },
		{ "A2", "D", "C2" },
		{ "A3", "B", "C3" },
	};

	private DataCollectionImpl mDataCollection;

	protected void setUp() throws Exception {
		super.setUp();
		mDataCollection = new DataCollectionImpl(HEADERS);
		for (String row[] : VALUES) {
			mDataCollection.addRecord(row);
		}
	}

	public void testSize() {
		assertEquals(VALUES.length, mDataCollection.size());
	}

	public void testGet() {
		for (int row = 0; row < VALUES.length; row++) {
			DataRecordImpl dataRecord = mDataCollection.get(row);
			for (int col = 0; col < HEADERS.length; col++) {
				DataEntry dataEntry = dataRecord.get(col);
				assertEquals(HEADERS[col], dataEntry.getKey());
				assertEquals(VALUES[row][col], dataEntry.getValue());
			}
		}
	}

	public void testIsFiltered() {
		assertFalse(mDataCollection.isFiltered());
		mDataCollection.setFiltered(true);
		assertTrue(mDataCollection.isFiltered());
	}

	public void testFilter() {
		int originalSize = mDataCollection.size();
		assertNull(mDataCollection.getColumnFilter(HEADERS[1]));
		mDataCollection.putColumnFilter(HEADERS[1], new ValueFilter() {
			@Override
			public boolean isMatch(String value) {
				return VALUES[0][1].equals(value);
			}
		});
		assertNotNull(mDataCollection.getColumnFilter(HEADERS[1]));
		
		// setting a filter doesn't turn on filtering
		assertEquals(originalSize, mDataCollection.size());
		assertEquals(originalSize, mDataCollection.sizeUnfiltered());
		
		// after turning on filtering, the sizes should be different
		mDataCollection.setFiltered(true);
		assertTrue(originalSize > mDataCollection.size());
		assertEquals(originalSize, mDataCollection.sizeUnfiltered());
	}

	public void testSearch() {
		assertEquals(-1, mDataCollection.search("foo", 0));
		assertEquals(0, mDataCollection.search(VALUES[0][0], 0));
		assertEquals(-1, mDataCollection.search(VALUES[0][0], 1));
		assertEquals(2, mDataCollection.search(VALUES[2][2], 0));
	}

}