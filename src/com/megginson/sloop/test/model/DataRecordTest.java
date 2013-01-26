package com.megginson.sloop.test.model;

import android.os.Parcel;

import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.DataRecord;

import junit.framework.TestCase;

public class DataRecordTest extends TestCase {
	
	private final static String KEY1 = "key 1";
	private final static String VALUE1 = "value 1";
	
	private final static String KEY2 = "key 2";
	private final static String VALUE2 = "value 2";
	
	private final static String KEY3 = "key 3";
	private final static String VALUE3 = "value 3";
	
	private final static String KEY4 = "key 4";
	private final static String VALUE4 = "value 4";
	
	private DataRecord mDataRecord;

	protected void setUp() throws Exception {
		super.setUp();
		mDataRecord = new DataRecord();
		mDataRecord.addEntry(KEY1, VALUE1);
		mDataRecord.addEntry(KEY2, VALUE2);
		mDataRecord.addEntry(KEY3, VALUE3);
	}
	
	public void testCopyConstructor () {
		DataRecord dataRecord = new DataRecord(mDataRecord);
		assertEquals(dataRecord.size(), mDataRecord.size());
		for (int i = 0; i < dataRecord.size(); i++) {
			assertEquals(dataRecord.get(i), mDataRecord.get(i));
			// expecting a deep copy
			assertNotSame(dataRecord.get(i), mDataRecord.get(i));
		}
	}

	public void testSize() {
		assertEquals(3, mDataRecord.size());
	}

	public void testAddEntry() {
		mDataRecord.addEntry(KEY4, VALUE4);
		assertEquals(4, mDataRecord.size());
		assertEquals(KEY4, mDataRecord.get(3).getKey());
		assertEquals(VALUE4, mDataRecord.get(3).getValue());
	}

	public void testGet() {
		assertEquals(KEY2, mDataRecord.get(1).getKey());
		assertEquals(VALUE2, mDataRecord.get(1).getValue());
	}

	public void testSetDataEntry() {
		mDataRecord.set(1, new DataEntry(KEY4, VALUE4));
		assertEquals(KEY4, mDataRecord.get(1).getKey());
		assertEquals(VALUE4, mDataRecord.get(1).getValue());
	}

	public void testAdd() {
		mDataRecord.add(1, new DataEntry(KEY4, VALUE4));
		assertEquals(4, mDataRecord.size());
		assertEquals(KEY4, mDataRecord.get(1).getKey());
		assertEquals(VALUE4, mDataRecord.get(1).getValue());
		assertEquals(KEY2, mDataRecord.get(2).getKey());
		
		mDataRecord.add(new DataEntry(KEY1, VALUE1));
		assertEquals(5, mDataRecord.size());
		assertEquals(KEY1, mDataRecord.get(4).getKey());
	}

	public void testRemove() {
		mDataRecord.remove(1);
		assertEquals(2, mDataRecord.size());
		
		mDataRecord.remove(new DataEntry(KEY1, VALUE1));
		assertEquals(1, mDataRecord.size());
	}

	public void testDescribeContents() {
		assertEquals(0, mDataRecord.describeContents());
	}

	public void testWriteToParcel() {
		Parcel parcel = Parcel.obtain();
		mDataRecord.writeToParcel(parcel, 0);
		
		parcel.setDataPosition(0);
		int size = parcel.readInt();
		assertEquals(size, mDataRecord.size());
		for (int i = 0; i < size; i++) {
			DataEntry dataEntry = (DataEntry)parcel.readParcelable(DataEntry.class.getClassLoader());
			assertEquals(dataEntry, mDataRecord.get(i));
		}
	}

	public void testEquals() {
		DataRecord dataRecord = new DataRecord(mDataRecord);
		assertEquals(dataRecord, mDataRecord);
		
		dataRecord.get(1).setValue(VALUE4);
		assertFalse(dataRecord.equals(mDataRecord));
		assertFalse(mDataRecord.equals(dataRecord));
	}

	public void testHashCode() {
		DataRecord dataRecord = new DataRecord(mDataRecord);
		assertEquals(dataRecord.hashCode(), mDataRecord.hashCode());
		
		dataRecord.get(1).setValue(VALUE4);
		assertFalse(dataRecord.hashCode() == mDataRecord.hashCode());
	}

	public void testClear() {
		mDataRecord.clear();
		assertEquals(0, mDataRecord.size());
	}

	public void testIndexOf() {
		assertEquals(1, mDataRecord.indexOf(new DataEntry(KEY2, VALUE2)));
	}

	public void testLastIndexOf() {
		DataEntry dataEntry = new DataEntry(KEY2, VALUE2);
		mDataRecord.add(dataEntry);
		assertEquals(1, mDataRecord.indexOf(dataEntry));
		assertEquals(3, mDataRecord.lastIndexOf(dataEntry));
	}
	
	public void testCreator() {
		Parcel parcel = Parcel.obtain();
		mDataRecord.writeToParcel(parcel, 0);
		
		parcel.setDataPosition(0);
		DataRecord dataRecord = DataRecord.CREATOR.createFromParcel(parcel);
		assertEquals(dataRecord, mDataRecord);
	}

}
