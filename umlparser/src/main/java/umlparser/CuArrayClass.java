package umlparser;

import java.io.*;
import java.util.*;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;

public class CuArrayClass {


    public ArrayList<CompilationUnit> getcompilationUnitArray(final String inputPath)
            throws Exception {

         //find the folder and parse through all the files in it convert it into compilation unit and add it to
                //compilation unit array
        final File dir = new File(inputPath);
        final ArrayList<CompilationUnit> compilationUnitArray2; 
        final File[] listOfFiles = dir.listFiles();
        
        compilationUnitArray2 = new ArrayList<CompilationUnit>();
        if (listOfFiles != null)
			parseFiles(compilationUnitArray2, listOfFiles);
		return compilationUnitArray2;
    }

	public void parseFiles(ArrayList<CompilationUnit> compilationUnitArray2, File[] listOfFiles)
			throws FileNotFoundException, ParseException, IOException {
		for (File child : listOfFiles) {
		    if (child.isFile() && child.getName().endsWith(".java")){
		         FileInputStream fileInputStream = new FileInputStream(child);
		         CompilationUnit compilationUnit;
		    try {
		        compilationUnit = JavaParser.parse(fileInputStream);
		        compilationUnitArray2.add(compilationUnit);
		    } finally {
		        fileInputStream.close();
		    }
		}
      }
	} 
	
}