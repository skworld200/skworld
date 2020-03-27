package entryPoint;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.Constants;
import factory.SearchFactory;
import service.Search;

public class PerformanceMain {

	public static void main(String[] args) {
		long start_time=System.currentTimeMillis();
		SearchFactory factory = new SearchFactory();

		//Indexed performance test for 2000000 with 6 different String took 7 minutes = 1st rank
	/*	String searchType = Constants.INDEXED_SEARCH;
		Search search = factory.getSearch(searchType);
		String [] userVal = {"is", "with", "...ctronic","2005","ABCDEFGHI","\\bthe\\b"};
		for (int i=0;i<2000000;i++) {	
			for(int k=0 ; k<userVal.length; k++) {								
				System.out.println("Searching term: "+userVal[k]);
				search.getSearchResult(userVal[k]);
				search.print();
			}			
		}
		search.printEndTime(start_time);*/	

		//Regular expression performance test for single string search and 2000000 times took 57 minutes = 2nd rank
		String searchType = Constants.REG_EXPR_SEARCH;	
		String str = "\\bthe\\b";
		Search search = factory.getSearch(searchType);
		Matcher matcher = Pattern.compile(str).matcher("");
		for (int i=0;i<2000000;i++) {			
			search.getSearchResult(str, matcher);
			search.print();						
		}
		search.printEndTime(start_time);	

		//String matching performance test for 2000000 with 6 different String took 82 minutes = 3rd rank
		/*String searchType = Constants.STRING_SEARCH;
		String str = "THE";
		Search search = factory.getSearch(searchType);		
		for (int i=0;i<2000000;i++) {			
			search.getSearchResult(str);
			search.print();						
		}
		search.printEndTime(start_time);*/
	}
}