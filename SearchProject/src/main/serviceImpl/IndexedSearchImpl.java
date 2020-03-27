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

/*This class perform Indexed searching.
 * It reads the file and stores all the words with its count with the file in a Map
 * The search method runs on the map records
 * Match does not include substrings, does not support multi-word phrases*/
public class IndexedSearchImpl extends Search {

	private static Map<File, Map<String,Integer>> indexedMap =  new HashMap<File, Map<String,Integer>>(); 

	@Override
	public void  getSearchResult(String str) {
		str = str.toLowerCase();
		Map<String,Integer> wordSearchMap =  new HashMap<String,Integer>(); 
		getIndexedResult();
		for (Map.Entry<File, Map<String,Integer>> entry : indexedMap.entrySet()) {
			Map<String,Integer> val = entry.getValue();
			if(val.containsKey(str)) {
				wordSearchMap.put(entry.getKey().getName(),val.get(str) );		
			}else {
				wordSearchMap.put(entry.getKey().getName(),0 );	
			}
		}		

		this.setDocRecords(wordSearchMap);
	}

	private void getIndexedResult(){

		if(indexedMap.isEmpty()) {
			String st;
			String words[]=null;

			for(File file : getAllFiles()) {
				if(file.isFile()) {	

					try (Reader reader = new FileReader(file);
							BufferedReader bf = new BufferedReader(reader)) {

						Map<String,Integer> wordCountMap = new HashMap<String,Integer>();
						while ((st = bf.readLine()) != null) {						
							words = st.split(Constants.SPACE);
							for (String word : words) 
							{	
								word = word.toLowerCase();
								Integer j = wordCountMap.get(word); 
								wordCountMap.put(word, (j == null) ? 1 : j + 1); 
							}
						}					
						indexedMap.put(file, wordCountMap);

					}catch(IOException e) {
						e.printStackTrace();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
