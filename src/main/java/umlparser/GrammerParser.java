package umlparser;

import java.util.*;

import com.github.javaparser.ast.*;


public class GrammerParser {
    final String i;
    final String o;
    
  
    GrammerParser(final String inputPath, final String outputPath) {
        i = inputPath;
        o = inputPath + "\\" + outputPath + ".png";
       }
    
    
    HashMap<String, Boolean> hashmap  = new HashMap<String, Boolean>();
    HashMap<String, String> hashmapClassConnection  = new HashMap<String, String>();
    String code ="";
    ArrayList<CompilationUnit> compilationUnitArray;


    public void run() throws Exception {
    	
    	CuArrayClass obj = new CuArrayClass();
    	compilationUnitArray = obj.getcompilationUnitArray(i);
    	
        MapBuild mb = new MapBuild(hashmap, compilationUnitArray);
        mb.mapBuild(compilationUnitArray);
        
        ParserClass pc = new ParserClass(hashmap, hashmapClassConnection);
        for (Iterator<CompilationUnit> c = compilationUnitArray.iterator(); c.hasNext();) {
			CompilationUnit cu = c.next();
			code += pc.parser(cu);
		}
        ParseClassDependencies parseClassDependencies = new ParseClassDependencies(hashmap, hashmapClassConnection);
        code += parseClassDependencies.parseClassDependencies();
        System.out.println("YUML Grammer: " + code);
        GenerateUMLDiagram.generatePNG(code, o);
    }

       
}