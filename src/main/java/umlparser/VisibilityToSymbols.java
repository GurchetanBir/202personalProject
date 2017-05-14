package umlparser;


public class VisibilityToSymbols {

	 public String visibilityToSymbols(String stringVisibility) {
	        switch (stringVisibility) {
	        case "private":
	            return "-";
	        case "public":
	            return "+";
	        default:
	            return "";
	        }
	    }
	
}


