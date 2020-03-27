package serviceImpl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;


import service.Search;

/*This class perform Regular expression searching.
 *Match includes substrings, supports multi-word phrases*/
public class RegularExpSearchImpl extends Search {
	Matcher matcher=null;

	public void getSearchResult(String str,Matcher matcher) {
		this.matcher = matcher;
		getSearchResult(str);
	}

	@Override
	public void  getSearchResult(String str) {
	
		Map<String,Integer> hm =  new HashMap<String,Integer>(); 	 
		
		for(File file : getAllFiles()) {
			if(file.isFile()) {
				int cnt=0;

				try (Scanner scnr =  new Scanner(file)) {									
					while(scnr.hasNextLine()){
						String line = scnr.nextLine();	
						matcher=matcher.reset(line.toLowerCase());
						while(matcher.find()) {
							cnt++;
						}
					}
					hm.put(file.getName(), cnt);	
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.setDocRecords(hm);	
	}


}
