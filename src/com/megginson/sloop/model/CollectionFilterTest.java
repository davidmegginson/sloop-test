package com.megginson.sloop.model;

import com.megginson.sloop.model.impl.ContainsStringFilter;
import com.megginson.sloop.model.impl.EqualsStringFilter;

import junit.framework.TestCase;

public class CollectionFilterTest extends TestCase {

	private CollectionFilter mCollectionFilter;

	protected void setUp() throws Exception {
		super.setUp();
		mCollectionFilter = new CollectionFilter();
	}

	public void testTextFilter() {
		assertNull(mCollectionFilter.getTextFilter());
		mCollectionFilter.setTextFilter(new ContainsStringFilter("bar"));
		assertNotNull(mCollectionFilter.getTextFilter());
		assertEquals("*~bar", mCollectionFilter.toString());
	}

	public void testColumnFilters() {
		assertNotNull(mCollectionFilter.getColumnFilters());
		assertNull(mCollectionFilter.getColumnFilters().get("foo"));
		mCollectionFilter.getColumnFilters()
				.put("foo", new EqualsStringFilter("bar"));
		assertNotNull(mCollectionFilter.getColumnFilters().get("foo"));
		assertEquals("foo=bar", mCollectionFilter.toString());
	}

}
