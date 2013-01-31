package com.megginson.sloop.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.megginson.sloop.io.DataCollectionIO;
import com.megginson.sloop.model.DataCollection;
import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.DataRecord;

public class DataCollectionIOTest extends InstrumentationTestCase {

	private final static String TEST_FILE = "test-collection.csv";
	private final static int RECORD_COUNT = 5;
	private final static int ENTRY_COUNT = 5;

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testReadCSV() throws IOException {
		InputStream input = getInstrumentation().getContext().getAssets().open(TEST_FILE, AssetManager.ACCESS_BUFFER);
		Reader reader = new InputStreamReader(input, Charset.forName("utf8"));
		try {
			DataCollection dataCollection = DataCollectionIO.readCSV(reader);
			assertEquals(RECORD_COUNT, dataCollection.size());
			for (int i = 0; i < RECORD_COUNT; i++) {
				DataRecord dataRecord = dataCollection.get(i);
				assertEquals(ENTRY_COUNT, dataRecord.size());
				for (int j = 0; j < ENTRY_COUNT; j++) {
					DataEntry dataEntry = dataRecord.get(j);
					assertEquals(makeKey(i, j), dataEntry.getKey());
					assertEquals(makeValue(i, j), dataEntry.getValue());
				}
			}
		} finally {
			reader.close();
		}
	}

	private String makeKey(int row, int col) {
		return "Key " + col;
	}

	private String makeValue(int row, int col) {
		return "Value " + row + "," + col;
	}

}
