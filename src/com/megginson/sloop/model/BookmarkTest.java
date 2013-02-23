package com.megginson.sloop.model;

import junit.framework.TestCase;

import com.megginson.sloop.model.Bookmark;

public class BookmarkTest extends TestCase {

	private final static String TEST_URL = "http://example.org/example.csv";

	private final static String TEST_TITLE = "Bookmark Example";

	private final static String TEST_URL2 = "http://example.org/example-alt.csv";

	private final static String TEST_TITLE2 = "Alternative Bookmark Example";

	private Bookmark mBookmark;

	protected void setUp() throws Exception {
		super.setUp();
		mBookmark = new Bookmark(TEST_URL, TEST_TITLE);
	}

	public void testConstructor() {
		Bookmark bookmark = new Bookmark(TEST_URL);
		assertEquals(TEST_URL, bookmark.getUrl());
		assertEquals(TEST_URL, bookmark.getTitle());
	}

	public void testCopyConstructor() {
		Bookmark copy = new Bookmark(mBookmark);
		assertNotSame(copy, mBookmark);
		assertEquals(copy, mBookmark);
	}

	public void testGetTitle() {
		assertEquals(TEST_TITLE, mBookmark.getTitle());
	}

	public void testSetTitle() {
		mBookmark.setTitle(TEST_TITLE2);
		assertEquals(TEST_TITLE2, mBookmark.getTitle());
	}

	public void testGetUrl() {
		assertEquals(TEST_URL, mBookmark.getUrl());
	}

	public void testSetUrl() {
		mBookmark.setUrl(TEST_URL2);
		assertEquals(TEST_URL2, mBookmark.getUrl());
	}

	public void testEquals() {
		assertFalse(mBookmark.equals(null));
		assertTrue(mBookmark.equals(mBookmark));
		assertFalse(mBookmark.equals(new Bookmark()));
		assertFalse(mBookmark.equals(new Bookmark(TEST_URL)));
		assertTrue(mBookmark.equals(new Bookmark(TEST_URL, TEST_TITLE)));
		assertFalse(mBookmark.equals(new Bookmark(TEST_URL, TEST_TITLE2)));
		assertFalse(mBookmark.equals(new Bookmark(TEST_URL2, TEST_TITLE)));
	}

	public void testHashCode() {
		Bookmark bookmark = new Bookmark(TEST_URL, TEST_TITLE);
		assertEquals(bookmark.hashCode(), mBookmark.hashCode());

		bookmark = new Bookmark(TEST_URL, TEST_TITLE2);
		assertTrue(bookmark.hashCode() != mBookmark.hashCode());

		bookmark = new Bookmark(TEST_URL2, TEST_TITLE);
		assertTrue(bookmark.hashCode() != mBookmark.hashCode());

		bookmark = new Bookmark();
		assertTrue(bookmark.hashCode() != mBookmark.hashCode());

	}

	public void testToString() {
		assertEquals("{Bookmark|" + TEST_URL + "|" + TEST_TITLE + "}",
				mBookmark.toString());
	}

}
