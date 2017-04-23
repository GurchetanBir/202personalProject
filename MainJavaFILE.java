import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sourceforge.plantuml.SourceStringReader;




public class Main_Class {
	public static void main (String args[])
	{
		if(args!= null)
		{
		
			try
			{
				CodeParser codeparser = new CodeParser();
				StringBuilder stringbuilder = codeparser.Read("/Users/chetansidhu/Desktop/A");
				System.out.println(stringbuilder.toString());
				SourceStringReader plantUmlReader = new SourceStringReader(stringbuilder.toString());
				try(FileOutputStream imageOutputStream = new FileOutputStream("/Users/chetansidhu/Desktop/umldiagram.png")) {
	                plantUmlReader.generateImage(imageOutputStream);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			}
			catch(Exception e)
			{
				System.out.println("Error");
			}
		}
	}

}
