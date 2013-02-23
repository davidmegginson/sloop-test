package com.megginson.sloop.model.impl;

import junit.framework.TestCase;
import android.os.Parcel;

import com.megginson.sloop.model.DataEntry;

public class DataRecordTest extends TestCase {

	private final static String HEADERS[] = { "Column 1", "Column 2",
			"Column 3", };

	private final static String VALUES[] = { "Value 1", "Value 2", "Value 3", };

	private final static boolean FILTER_FLAGS[] = { false, true, false, };

	private DataRecordImpl mDataRecord;

	protected void setUp() throws Exception {
		super.setUp();
		mDataRecord = new DataRecordImpl(HEADERS, VALUES, FILTER_FLAGS);
	}

	public void testSize() {
		assertEquals(HEADERS.length, mDataRecord.size());
	}

	public void testGetInt() {
		for (int i = 0; i < HEADERS.length; i++) {
			DataEntry entry = mDataRecord.get(i);
			assertEquals(HEADERS[i], entry.getKey());
			assertEquals(VALUES[i], entry.getValue());
			assertEquals(FILTER_FLAGS[i], entry.hasFilter());
		}
	}
	
	public void testGetString() {
		for (int i = 0; i < HEADERS.length; i++) {
			DataEntry entry = mDataRecord.get(HEADERS[i]);
			assertEquals(HEADERS[i], entry.getKey());
			assertEquals(VALUES[i], entry.getValue());
			assertEquals(FILTER_FLAGS[i], entry.hasFilter());
		}		
	}

	public void testDescribeContents() {
		assertEquals(0, mDataRecord.describeContents());
	}

	public void testCreator() {
		Parcel parcel = Parcel.obtain();
		mDataRecord.writeToParcel(parcel, 0);

		parcel.setDataPosition(0);
		DataRecordImpl dataRecordImpl = DataRecordImpl.CREATOR
				.createFromParcel(parcel);
		assertEquals(dataRecordImpl, mDataRecord);
	}

}
