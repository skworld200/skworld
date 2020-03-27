package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import constants.Constants;
import factory.SearchFactory;
import service.Search;

public class TestIndexedSearchImpl {
	
	@Test	
	public void testGetSearchResult() {
		
		SearchFactory factory = new SearchFactory();
		String searchType = Constants.INDEXED_SEARCH;
		Search search = factory.getSearch(searchType);
		String searchWord="with";
		search.getSearchResult(searchWord);	
		StringBuilder builder = search.print();		

		StringBuilder sb = new StringBuilder();
		sb.append("Search Results:");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());		
		sb.append("french_armed_forces.txt - 6 Matches");
		sb.append(System.lineSeparator());
		sb.append("warp_drive.txt - 2 Matches");
		sb.append(System.lineSeparator());
		sb.append("hitchhikers.txt - 0 Matches");
		sb.append(System.lineSeparator());		
				

		assertEquals("Result: ", sb.toString(), builder.toString());
	}

}
