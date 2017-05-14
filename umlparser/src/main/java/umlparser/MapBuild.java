package umlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

public class MapBuild {
	
	HashMap<String, Boolean> hashmap;
	ArrayList<CompilationUnit> compilationUnitArray;

	 
	 public MapBuild(HashMap<String, Boolean> map,ArrayList<CompilationUnit> compilationUnitArray) {
			this.hashmap = map;
		    this.compilationUnitArray=compilationUnitArray;
		}
	
	
	public void mapBuild(final ArrayList<CompilationUnit> compilationUnitArray) {
	    	
	        try {
				for (final Iterator<CompilationUnit> i = compilationUnitArray.iterator(); i.hasNext();) {
					
					final CompilationUnit compilationUnit = i.next();
					final List<TypeDeclaration> compilationUnitList = compilationUnit.getTypes();
					
				    addInterface(compilationUnitList);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    }


	private void addInterface(final List<TypeDeclaration> compilationUnitList) {
		try {
			for (final Iterator<TypeDeclaration> it = compilationUnitList.iterator(); it.hasNext();) {
				final Node node = it.next();
				final ClassOrInterfaceDeclaration classOrInterface = (ClassOrInterfaceDeclaration) node;
				hashmap.put(classOrInterface.getName(), classOrInterface.isInterface());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
