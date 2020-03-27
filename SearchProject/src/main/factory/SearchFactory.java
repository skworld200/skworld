package factory;

import constants.Constants;

import service.Search;
import serviceImpl.IndexedSearchImpl;
import serviceImpl.RegularExpSearchImpl;
import serviceImpl.StringSearchImpl;

/*This is a Factory design pattern */
public class SearchFactory {

	public Search getSearch(String searchType){  
		if(searchType == null){  
			return null;  
		}  
		if(searchType.equalsIgnoreCase(Constants.STRING_SEARCH)) {  
			return new StringSearchImpl();  
		}   
		else if(searchType.equalsIgnoreCase(Constants.REG_EXPR_SEARCH)){  
			return new RegularExpSearchImpl();  
		}   
		else if(searchType.equalsIgnoreCase(Constants.INDEXED_SEARCH)) {  
			return new IndexedSearchImpl();  
		}  
		return null;  
	}  

}
