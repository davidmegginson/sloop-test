package com.megginson.sloop.model.impl;

import junit.framework.TestCase;
import android.os.Parcel;

import com.megginson.sloop.model.DataEntry;

public class DataEntryTest extends TestCase {
	
	private final static String KEY = "test key";
	private final static String VALUE = "test value";
	private final static String KEY2 = "alternative test key";
	private final static String VALUE2 = "alternative test value";
	
	private DataEntryImpl mDataEntry;

	protected void setUp() throws Exception {
		super.setUp();
		mDataEntry = new DataEntryImpl(KEY, VALUE);
	}
	
	public void testHashCode() {
		DataEntryImpl dataEntryImpl = new DataEntryImpl(KEY, VALUE);
		assertEquals(dataEntryImpl.hashCode(), mDataEntry.hashCode());
		dataEntryImpl.setValue(VALUE2);
		assertTrue(dataEntryImpl.hashCode() != mDataEntry.hashCode());
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

		DataEntry dataEntryImpl = new DataEntryImpl(KEY, VALUE);
		assertEquals(dataEntryImpl, mDataEntry);
		
		dataEntryImpl = new DataEntryImpl(KEY2, VALUE);
		assertFalse(mDataEntry.equals(dataEntryImpl));
		
		dataEntryImpl = new DataEntryImpl(KEY, VALUE2);
		assertFalse(mDataEntry.equals(dataEntryImpl));
		
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
		DataEntry dataEntryImpl = DataEntryImpl.CREATOR.createFromParcel(parcel);
		assertEquals(dataEntryImpl, mDataEntry);
	}

}
