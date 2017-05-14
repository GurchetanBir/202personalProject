package umlparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ParseClassDependencies {
	
	

	HashMap<String, Boolean> hashmap;
    HashMap<String, String> hashMapClassConnection;

	
	
	public ParseClassDependencies(HashMap<String, Boolean> hashmap, HashMap<String, String> hashMapClassConnection) {
		this.hashmap=hashmap;
	    this.hashMapClassConnection=hashMapClassConnection;
	}
	
	
	 public String parseClassDependencies() {
	        
	        
	        
			final Set<String> keys = hashMapClassConnection.keySet();
			//System.out.println(mapClassConn.keySet());
			// [A-B, A-C, A-D] for my test case
			String parseClassDependenciesResult = "";
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				final String s = i.next();
				//System.out.println(i);
				//A-B 
				//A-C 
				//A-D
			    final String[] classNames = s.split("-");
			    parseClassDependenciesResult = interfaceCheck(parseClassDependenciesResult, classNames);
			  //System.out.println(mapClassConn.get(s));
			    parseClassDependenciesResult += hashMapClassConnection.get(s);

			    
			    parseClassDependenciesResult = classCheck(parseClassDependenciesResult, classNames);
			    parseClassDependenciesResult += ",";
			}
			return parseClassDependenciesResult;
			
		
 }

	private String classCheck(String parseClassDependenciesResult, final String[] classNames) {
		try {
			parseClassDependenciesResult += hashmap.get(classNames[1]) ? "[<<interface>>;" + classNames[1] + "]" : "[" + classNames[1] + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseClassDependenciesResult;
	}

	private String interfaceCheck(String parseClassDependenciesResult, final String[] classNames) {
		try {
			parseClassDependenciesResult += hashmap.get(classNames[0]) ? "[<<interface>>;" + classNames[0] + "]" : "[" + classNames[0] + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseClassDependenciesResult;
	}

	

}
