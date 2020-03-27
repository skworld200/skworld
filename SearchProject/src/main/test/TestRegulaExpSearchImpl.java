package test;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import constants.Constants;
import factory.SearchFactory;
import service.Search;

public class TestRegulaExpSearchImpl {

	@Test	
	public void testGetSearchResult() {

		SearchFactory factory = new SearchFactory();
		String searchType = Constants.REG_EXPR_SEARCH;
		Search search = factory.getSearch(searchType);
		String searchWord="...ctronic";
		Matcher matcher = Pattern.compile(searchWord).matcher("");
		search.getSearchResult(searchWord,matcher);	
		StringBuilder builder = search.print();		

		StringBuilder sb = new StringBuilder();
		sb.append("Search Results:");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());		
		sb.append("hitchhikers.txt - 1 Matches");
		sb.append(System.lineSeparator());
		sb.append("french_armed_forces.txt - 0 Matches");
		sb.append(System.lineSeparator());
		sb.append("warp_drive.txt - 0 Matches");
		sb.append(System.lineSeparator());				

		assertEquals("Result: ", sb.toString(), builder.toString());
	}

}
