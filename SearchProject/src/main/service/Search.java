package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;


import constants.Constants;
import utilities.CustomMapComparator;


public abstract class Search {

	private Map<String,Integer> docRecords = new HashMap<String,Integer>();	
	public abstract void getSearchResult(String str) ;

	public void getSearchResult(String str, Matcher matcher) {
		getSearchResult(str);
	}; 

	protected File[] getAllFiles() {
		File file = new File(Constants.FILE_PATH);
		File [] fl = file.listFiles();
		return fl;	
	}	
	public Map<String,Integer> getDocRecords(){
		return docRecords;		
	}
	public   void setDocRecords(Map<String,Integer> doc) {		
		docRecords=doc;		
	}

	public StringBuilder printEndTime(long start_time) {	
		StringBuilder builder = new StringBuilder();
		long end = System.currentTimeMillis();
		long timeElapsed = end - start_time;		
		builder.append("Elapsed Time: "+timeElapsed+" ms");
		System.out.println(builder.toString());
		return builder;		
	}

	public StringBuilder print() {
		StringBuilder builder = new StringBuilder();
		builder.append("Search Results:");
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());		

		Set<Entry<String, Integer>> set = getDocRecords().entrySet();	
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new CustomMapComparator());

		for(Map.Entry<String, Integer> entry:list){
			builder.append(entry.getKey());
			builder.append(" - ");
			builder.append(entry.getValue());	
			builder.append(" Matches");
			builder.append(System.lineSeparator());
		}	
		System.out.println(builder.toString());
		return builder;
	}

}
