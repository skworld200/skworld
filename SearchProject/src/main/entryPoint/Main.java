package entryPoint;



import java.util.regex.Pattern;

import constants.Constants;
import factory.SearchFactory;
import service.Search;

public class Main {

	public static void main(String[] args) {
		long start_time=System.currentTimeMillis();		
		SearchFactory factory = new SearchFactory();

		//String match test
	/*	String searchType = Constants.STRING_SEARCH;		
		Search search = factory.getSearch(searchType);	
		String searchWord = "the";
		search.getSearchResult(searchWord,null);	
		search.print();
		search.printEndTime(start_time);*/

		//String regular expression test
		String searchType = Constants.REG_EXPR_SEARCH;
		String searchWord = "\\bthe\\b";
		Search search = factory.getSearch(searchType);			
		search.getSearchResult(searchWord, Pattern.compile(searchWord).matcher(""));	
		search.print();
		search.printEndTime(start_time);

		//Index search test
		/*	String searchType = Constants.INDEXED_SEARCH;
		String searchWord = "the";
		Search search = factory.getSearch(searchType);
		search.getSearchResult(searchWord,null);	
		search.print();
		search.printEndTime(start_time);	*/	
	}

}
