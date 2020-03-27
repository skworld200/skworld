package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


import constants.Constants;
import service.Search;

/*This class perform plain string searching.
 * Match does not include substrings, does not support multi-word phrases*/
public class StringSearchImpl extends Search {

	
	@Override
	public void getSearchResult(String str) {
		
		Map<String,Integer> hm =  new HashMap<String,Integer>(); 		
		String st;
		
		for(File file : getAllFiles()) {
			if(file.isFile()) {
				int cnt=0;

				try (Reader reader = new FileReader(file);
						BufferedReader bf = new BufferedReader(reader)) {
					while ((st = bf.readLine()) != null ) {
						String [] words = st.split(Constants.SPACE);
						for (String word : words) 
						{
							if (word.equalsIgnoreCase(str))  
							{
								cnt++;    
							}
						}						
					} 
					hm.put(file.getName(), cnt);	
				}catch(IOException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.setDocRecords(hm);
	}
}
