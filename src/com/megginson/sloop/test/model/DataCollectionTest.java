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
		mDataCollection = new DataCollection();
		for (int i = 0; i < RECORD_COUNT; i++) {
			DataRecord dataRecord = new DataRecord();
			for (int j = 0; j < ENTRY_COUNT; j++) {
				dataRecord.addEntry(makeKey(i, j), makeValue(i, j));
			}
			mDataCollection.add(dataRecord);
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
		for (int i = 0; i < RECORD_COUNT; i++) {
			DataRecord dataRecord = mDataCollection.get(i);
			for (int j = 0; j < ENTRY_COUNT; j++) {
				DataEntry dataEntry = dataRecord.get(j);
				assertEquals(makeKey(i, j), dataEntry.getKey());
				assertEquals(makeValue(i, j), dataEntry.getValue());
			}
		}
	}

	public void testSet() {
		mDataCollection.set(2, makeTestRecord());
		assertEquals(makeTestRecord(), mDataCollection.get(2));
	}

	public void testAdd() {
		// Add in the middle
		mDataCollection.add(1, makeTestRecord());
		assertEquals(RECORD_COUNT + 1, mDataCollection.size());
		assertEquals(makeTestRecord(), mDataCollection.get(1));

		// Add to the end
		mDataCollection.add(makeTestRecord());
		assertEquals(RECORD_COUNT + 2, mDataCollection.size());
		assertEquals(makeTestRecord(),
				mDataCollection.get(mDataCollection.size() - 1));
	}

	public void testRemove() {
		int position = 2;
		
		// Copy, to be safe
		DataRecord nextRecord = new DataRecord(mDataCollection.get(position + 1));
		
		mDataCollection.remove(position);
		assertEquals(RECORD_COUNT - 1, mDataCollection.size());
		assertEquals(nextRecord, mDataCollection.get(position));
		
		mDataCollection.remove(nextRecord);
		assertEquals(RECORD_COUNT - 2, mDataCollection.size());
	}

	public void testEquals() {
		DataCollection dataCollection = new DataCollection(mDataCollection);
		assertEquals(dataCollection, mDataCollection);
		assertEquals(mDataCollection, dataCollection);
		
		dataCollection.set(2, makeTestRecord());
		assertFalse(dataCollection.equals(mDataCollection));
		assertFalse(mDataCollection.equals(dataCollection));
		
		assertEquals(mDataCollection, mDataCollection);
		assertFalse(mDataCollection.equals(new Object()));
		assertFalse(mDataCollection.equals(null));
	}

	public void testHashCode() {
		DataCollection dataCollection = new DataCollection(mDataCollection);
		assertEquals(dataCollection.hashCode(), mDataCollection.hashCode());
		
		dataCollection.set(2, makeTestRecord());
		assertFalse(dataCollection.hashCode() == mDataCollection.hashCode());
	}

	public void testClear() {
		mDataCollection.clear();
		assertEquals(0, mDataCollection.size());
	}

	public void testIndexOf() {
		int position = 2;
		
		mDataCollection.set(position, makeTestRecord());
		assertEquals(position, mDataCollection.indexOf(makeTestRecord()));
	}

	public void testLastIndexOf() {
		int position = 2;
		
		mDataCollection.set(position, makeTestRecord());
		mDataCollection.add(makeTestRecord());
		assertEquals(position, mDataCollection.indexOf(makeTestRecord()));
		assertEquals(RECORD_COUNT, mDataCollection.lastIndexOf(makeTestRecord()));
	}

	private DataRecord makeTestRecord() {
		DataRecord dataRecord = new DataRecord();
		dataRecord.addEntry("KEY", "VALUE");
		return dataRecord;
	}

	private String makeKey(int row, int col) {
		return "Key " + row + "," + col;
	}

	private String makeValue(int row, int col) {
		return "Value " + row + "," + col;
	}

}
