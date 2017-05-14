package umlparser;

public class ChangeBrackets {

	
	public String changeBrackets(String paranth) {
        try {
        	paranth = paranth.replace("[", "(");
        	paranth = paranth.replace("]", ")");
        	paranth = paranth.replace("<", "(");
        	paranth = paranth.replace(">", ")");
		} catch (Exception e) {
			System.out.print("problem occured in Switching brackets");
			e.printStackTrace();
		}

        return paranth;
    }
}
