package com.megginson.sloop.test.model;

import android.os.Parcel;

import com.megginson.sloop.model.DataEntry;

import junit.framework.TestCase;

public class DataEntryTest extends TestCase {
	
	private final static String KEY = "test key";
	private final static String VALUE = "test value";
	private final static boolean HAS_FILTER = false;;
	private final static String KEY2 = "alternative test key";
	private final static String VALUE2 = "alternative test value";
	
	private DataEntry mDataEntry;

	protected void setUp() throws Exception {
		super.setUp();
		mDataEntry = new DataEntry(KEY, VALUE);
	}
	
	public void testCopyConstructor () {
		DataEntry dataEntry = new DataEntry(mDataEntry);
		assertEquals(dataEntry, mDataEntry);
	}

	public void testHashCode() {
		DataEntry dataEntry = new DataEntry(KEY, VALUE);
		assertEquals(dataEntry.hashCode(), mDataEntry.hashCode());
		dataEntry.setValue(VALUE2);
		assertTrue(dataEntry.hashCode() != mDataEntry.hashCode());
	}

	public void testGetKey() {
		assertEquals(KEY, mDataEntry.getKey());
	}

	public void testGetValue() {
		assertEquals(VALUE, mDataEntry.getValue());
	}

	public void testSetValue() {
		assertEquals(VALUE, mDataEntry.setValue(VALUE2));
		assertEquals(VALUE2, mDataEntry.getValue());
	}

	public void testDescribeContents() {
		assertEquals(0, mDataEntry.describeContents());
	}

	public void testWriteToParcel() {
		Parcel parcel = Parcel.obtain();
		mDataEntry.writeToParcel(parcel, 0);
		parcel.setDataPosition(0);
		assertEquals(KEY, parcel.readString());
		assertEquals(VALUE, parcel.readString());
	}

	public void testEqualsObject() {
		assertEquals(mDataEntry, mDataEntry);

		DataEntry dataEntry = new DataEntry(KEY, VALUE);
		assertEquals(dataEntry, mDataEntry);
		
		dataEntry = new DataEntry(KEY2, VALUE);
		assertFalse(mDataEntry.equals(dataEntry));
		
		dataEntry = new DataEntry(KEY, VALUE2);
		assertFalse(mDataEntry.equals(dataEntry));
		
		assertFalse(mDataEntry.equals(new Object()));
		assertFalse(mDataEntry.equals(null));
	}

	public void testToString() {
		assertEquals("{" + KEY + "=" + VALUE + "}", mDataEntry.toString());
	}
	
	public void testCreator() {
		Parcel parcel = Parcel.obtain();
		parcel.writeString(mDataEntry.getKey());
		parcel.writeString(mDataEntry.getValue());
		parcel.writeValue(mDataEntry.hasFilter());

		parcel.setDataPosition(0);
		DataEntry dataEntry = DataEntry.CREATOR.createFromParcel(parcel);
		assertEquals(dataEntry, mDataEntry);
	}

}
