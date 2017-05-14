package umlparser;

public class Umlparser {

    public static void main(String[] args) throws Exception {
        	String argument1 = args[0];
        	String argument2 = args[1];
        	try {
				GrammerParser parseEngine = new GrammerParser(argument1, argument2);
				parseEngine.run();
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("problem in main class");
			}

    }

}