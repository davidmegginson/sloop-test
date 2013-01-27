package com.megginson.sloop.test.model;

import com.megginson.sloop.model.DataCollection;
import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.DataRecord;

import junit.framework.TestCase;

public class DataCollectionTest extends TestCase {

	private final static int RECORD_COUNT = 10;
	private final static int ENTRY_COUNT = 10;

	private DataCollection mDataCollection;

	protected void setUp() throws Exception {
		super.setUp();
		
		// Make the header row
		String headers[] = new String[ENTRY_COUNT];
		for (int col = 0; col < ENTRY_COUNT; col++) {
			headers[col] = makeHeader(col);
		}
		
		mDataCollection = new DataCollection(headers);
		
		// Fill in the records
		for (int row = 0; row < RECORD_COUNT; row++) {
			String record[] = new String[ENTRY_COUNT];
			for (int col = 0; col < ENTRY_COUNT; col++) {
				record[col] = makeValue(row, col);
			}
			mDataCollection.addRecord(record);
		}
	}
	
	public void testCopyConstructor () {
		DataCollection dataCollection = new DataCollection(mDataCollection);
		for (int i = 0; i < RECORD_COUNT; i++) {
			assertEquals(dataCollection.get(i), mDataCollection.get(i));
			assertNotSame(dataCollection.get(i), mDataCollection.get(i));
		}
	}

	public void testSize() {
		assertEquals(RECORD_COUNT, mDataCollection.size());
	}

	public void testGet() {
		for (int row = 0; row < RECORD_COUNT; row++) {
			DataRecord dataRecord = mDataCollection.get(row);
			for (int col = 0; col < ENTRY_COUNT; col++) {
				DataEntry dataEntry = dataRecord.get(col);
				assertEquals(makeHeader(col), dataEntry.getKey());
				assertEquals(makeValue(row, col), dataEntry.getValue());
			}
		}
	}
	
	public void testSearch() {
		assertEquals(-1, mDataCollection.search("foo", 0));
		assertEquals(0, mDataCollection.search("0,0", 0));
		assertEquals(-1, mDataCollection.search("0,0", 1));
		assertEquals(3, mDataCollection.search("3,1", 0));
	}

	public void testEquals() {
		DataCollection dataCollection = new DataCollection(mDataCollection);
		assertEquals(dataCollection, mDataCollection);
		assertEquals(mDataCollection, dataCollection);
	}

	public void testHashCode() {
		DataCollection dataCollection = new DataCollection(mDataCollection);
		assertEquals(dataCollection.hashCode(), mDataCollection.hashCode());
	}

	private String makeHeader(int col) {
		return "Header " + col;
	}

	private String makeValue(int row, int col) {
		return "Value " + row + "," + col;
	}

}
