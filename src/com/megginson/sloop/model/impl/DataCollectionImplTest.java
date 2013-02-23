package com.megginson.sloop.model.impl;

import junit.framework.TestCase;

import com.megginson.sloop.model.DataCollection;
import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.DataRecord;
import com.megginson.sloop.model.ValueFilter;

public class DataCollectionImplTest extends TestCase {

	private final static String HEADERS[] = { "A", "B", "C", };
	
	private final static String VALUES[][] = {
		{ "A1", "B", "C1" },
		{ "A2", "D", "C2" },
		{ "A3", "B", "C3" },
	};

	// test against the interface, not the implementation
	private DataCollection mDataCollection;

	protected void setUp() throws Exception {
		super.setUp();
		mDataCollection = new DataCollectionImpl(HEADERS);
		for (String row[] : VALUES) {
			((DataCollectionImpl)mDataCollection).addRecord(row);
		}
	}

	public void testSize() {
		assertEquals(VALUES.length, mDataCollection.getFilteredRecords().size());
	}

	public void testGet() {
		for (int i = 0; i < VALUES.length; i++) {
			DataRecord dataRecord = mDataCollection.getFilteredRecords().get(i);
			for (int col = 0; col < HEADERS.length; col++) {
				DataEntry dataEntry = dataRecord.getEntries().get(col);
				assertEquals(HEADERS[col], dataEntry.getKey());
				assertEquals(VALUES[i][col], dataEntry.getValue());
			}
		}
	}

	public void testIsFiltered() {
		assertFalse(mDataCollection.isFilteringEnabled());
		mDataCollection.setFilteringEnabled(true);
		assertTrue(mDataCollection.isFilteringEnabled());
	}

	public void testColumnFilter() {
		int originalSize = mDataCollection.getFilteredRecords().size();
		assertNull(mDataCollection.getColumnFilter(HEADERS[1]));
		mDataCollection.putColumnFilter(HEADERS[1], new ValueFilter() {
			@Override
			public boolean isMatch(String value) {
				return VALUES[0][1].equals(value);
			}
		});
		assertNotNull(mDataCollection.getColumnFilter(HEADERS[1]));
		
		// setting a filter doesn't turn on filtering
		assertEquals(originalSize, mDataCollection.getFilteredRecords().size());
		assertEquals(originalSize, mDataCollection.getRecords().size());
		
		// after turning on filtering, the sizes should be different
		mDataCollection.setFilteringEnabled(true);
		assertTrue(originalSize > mDataCollection.getFilteredRecords().size());
		assertEquals(2, mDataCollection.getFilteredRecords().size());
		assertEquals(originalSize, mDataCollection.getRecords().size());
	}
	
	public void testTextFilter() {
		int originalSize = mDataCollection.getFilteredRecords().size();
		assertNull(mDataCollection.getTextFilter());
		mDataCollection.setTextFilter(new ValueFilter() {
			@Override
			public boolean isMatch(String value) {
				return value.contains("B");
			}
		});
		assertNotNull(mDataCollection.getTextFilter());
		
		// setting a filter doesn't turn on filtering
		assertEquals(originalSize, mDataCollection.getFilteredRecords().size());
		assertEquals(originalSize, mDataCollection.getRecords().size());
		
		// after turning on filtering, the sizes should be different
		mDataCollection.setFilteringEnabled(true);
		assertTrue(originalSize > mDataCollection.getFilteredRecords().size());
		assertEquals(2, mDataCollection.getFilteredRecords().size());
		assertEquals(originalSize, mDataCollection.getRecords().size());		
	}

}
