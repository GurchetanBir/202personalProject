package umlparser;

import java.util.ArrayList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;

public class ParserClass {

	
	HashMap<String, Boolean> map;
    HashMap<String, String> hashMapClassConnection;

	
	
	public ParserClass(HashMap<String, Boolean> map, HashMap<String, String> hashMapClassConnection) {
		this.map=map;
	    this.hashMapClassConnection=hashMapClassConnection;
	}

	  public String parser(CompilationUnit cu) {
	        String finalresult = "";
	        String className = "";
	        String classShortName = "";
	        String functions = "";
	        String fields = "";
	        String add = ",";
	        ArrayList<String> makeFieldPublic;
	        List<TypeDeclaration> listTypeDeclaration;
	        Node node;
	        ClassOrInterfaceDeclaration classOrInterface;
	        
	        
	        makeFieldPublic= new ArrayList<String>();
	        listTypeDeclaration= cu.getTypes();
	        node = listTypeDeclaration.get(0);
	        classOrInterface = (ClassOrInterfaceDeclaration) node;



	        className = classOrInterface.isInterface() ? "[" + "<<interface>>;" : "[";
	        className += classOrInterface.getName();
	        classShortName = classOrInterface.getName();

	        boolean nextParameter = false;
	        for (final Iterator<BodyDeclaration> cust = ((TypeDeclaration) node).getMembers().iterator(); cust.hasNext();) {
				final BodyDeclaration bodyDeclaration = cust.next();
				if (bodyDeclaration instanceof ConstructorDeclaration) {
	                if (((ConstructorDeclaration) bodyDeclaration).getDeclarationAsString().startsWith("public") && !classOrInterface.isInterface()) {
	                	functions = nextParam(functions, nextParameter);
	                	functions += "+ " + ((ConstructorDeclaration) bodyDeclaration).getName() + "(";
	                    
	                    for (final Iterator<Node> getchildnode = ((ConstructorDeclaration) bodyDeclaration).getChildrenNodes().iterator(); getchildnode.hasNext();) {
							final Object getChildNode = getchildnode.next();
							if (getChildNode instanceof Parameter) {
	                            final String paramClass = ((Parameter) getChildNode).getType().toString();
	                            final String paramName = ((Parameter) getChildNode).getChildrenNodes().get(0).toString();
	                            functions += paramName + " : " + paramClass;
	                            if (map.containsKey(paramClass)&& !map.get(classShortName)) {
	                            	add += "[" + classShortName+ "] uses -.->";
	                                add = paramClassAddition(add, paramClass);
	                            }
	                            add += ",";
	                        }
						}
	                    functions += ")";
	                    nextParameter = true;
	                }
	            }
			}
	        
	        
	        for (Iterator<BodyDeclaration> mthd = ((TypeDeclaration) node).getMembers().iterator(); mthd.hasNext();) {
				final BodyDeclaration bodyDeclaration = mthd.next();
				if (bodyDeclaration instanceof MethodDeclaration)
					if (((MethodDeclaration) bodyDeclaration).getDeclarationAsString().startsWith("public") && !classOrInterface.isInterface()) {
						
	                    if (((MethodDeclaration) bodyDeclaration).getName().startsWith("get") || ((MethodDeclaration) bodyDeclaration).getName().startsWith("set")) {
	                        getset(makeFieldPublic, bodyDeclaration);
	                    } else {
	                    	
	                    	functions = nextParam(functions, nextParameter);
	                    	functions += "+ " + ((MethodDeclaration) bodyDeclaration).getName() + "(";
	                        
	                        
	                        for (Iterator<Node> getchildnode = ((MethodDeclaration) bodyDeclaration).getChildrenNodes().iterator(); getchildnode.hasNext();) {
								final Object getcn = getchildnode.next();
								if (getcn instanceof Parameter) {
	                                final String paramClass = ((Parameter) getcn).getType().toString();
	                                final String paramName = ((Parameter) getcn).getChildrenNodes().get(0).toString();
	                                functions += paramName + " : " + paramClass;
	                                add = paramClassAddition2(classShortName, add, paramClass);
	                            }else {
	                                final String methodBody[] = getcn.toString().split(" ");
	                                for (int i = 0; i < methodBody.length; i++) {
										final String param = methodBody[i];
										if (map.containsKey(param) && !map.get(classShortName)) {
											add += "[" + classShortName + "] uses -.->";
	                                        
											add = paramClassAddition(add, param);
											add += ",";
	                                    }
									}
	                            }
							}
	                        functions += ") : " + ((MethodDeclaration) bodyDeclaration).getType();
	                        nextParameter = true;
	                    }
	                }
			}
	        fields = parseFields(classShortName, fields, makeFieldPublic, node);
	        
	        
	        
	        // Check extends, implements
	        add = checkExtends(classShortName, add, classOrInterface);
	        add = checkImplements(classShortName, add, classOrInterface);
	        // Combine className, methods and fields
	        finalresult += className;
	        finalresult = combineFieldsOrFunction(finalresult, fields);
	        finalresult = combineFieldsOrFunction(finalresult, functions);
	        finalresult += "]";
	        finalresult += add;
	        return finalresult;
}

	private String combineFieldsOrFunction(String finalresult, String fields) {
		if (!fields.isEmpty()) {
			final ChangeBrackets cb = new ChangeBrackets();

			finalresult += "|" + cb.changeBrackets(fields);
		}
		return finalresult;
	}

	private String checkImplements(String classShortName, String add, ClassOrInterfaceDeclaration classOrInterface) {
		List<ClassOrInterfaceType> interfaceList;
		if (classOrInterface.getImplements() != null) {
		     interfaceList = (List<ClassOrInterfaceType>) classOrInterface.getImplements();
		    for (ClassOrInterfaceType intface : interfaceList) {
		    	add += "[" + classShortName + "] " + "-.-^ " + "["
		                + "<<interface>>;" + intface + "]";
		    	add += ",";
		    }
		}
		return add;
	}

	private String checkExtends(String classShortName, String add, ClassOrInterfaceDeclaration classOrInterface) {
		if (classOrInterface.getExtends() != null) {
			add += "[" + classShortName + "] " + "-^ " + classOrInterface.getExtends();
		    add += ",";
		}
		return add;
	}

	private String parseFields(String classShortName, String fields, ArrayList<String> makeFieldPublic, Node node) {
		boolean nextArgument = false;
		for (BodyDeclaration bodyDeclaration : ((TypeDeclaration) node).getMembers()) {
		    if (bodyDeclaration instanceof FieldDeclaration) {
		    	
		        VisibilityToSymbols v = new VisibilityToSymbols();
		        String scopeOfField = v.visibilityToSymbols(bodyDeclaration.toStringWithoutComments().substring(0,bodyDeclaration.toStringWithoutComments().indexOf(" ")));
		        
		        ChangeBrackets cb = new ChangeBrackets();
		        String fieldClassName = cb.changeBrackets(((FieldDeclaration) bodyDeclaration).getType().toString());
		        String fieldN = ((FieldDeclaration) bodyDeclaration).getChildrenNodes().get(1).toString();
		        
		        fieldN = equalFieldName(bodyDeclaration, fieldN);
		        scopeOfField = changeScope(makeFieldPublic, scopeOfField, fieldN);
		        
		        String getDependencies = "";
		        boolean getMultiplicity = false;
		        
		        if (fieldClassName.contains("(")) {
		        	getDependencies = fieldClassName.substring(fieldClassName.indexOf("(") + 1,
		            		fieldClassName.indexOf(")"));
		        	getMultiplicity = true;
		        } else if (map.containsKey(fieldClassName)) {
		        	getDependencies = fieldClassName;
		        }
		        if (getDependencies.length() > 0 && map.containsKey(getDependencies)) {
		            String conn = "-";

		            connections(classShortName, getDependencies, getMultiplicity, conn);
		        }
		        if ((scopeOfField == "+" || scopeOfField == "-")) {
		            fields = nextArgumentChange(fields, nextArgument, scopeOfField, fieldClassName, fieldN);
		            nextArgument = true;
		        } else {
				}
		    }

		}
		return fields;
	}

	private void connections(String classShortName, String getDependencies, boolean getMultiplicity, String conn) {
		if (hashMapClassConnection.containsKey(getDependencies + "-" + classShortName)) {
			conn = hashMapClassConnection.get(getDependencies + "-" + classShortName);
		    if (getMultiplicity)
		    	conn = "*" + conn;
		    hashMapClassConnection.put(getDependencies + "-" + classShortName,
		    		conn);
		} else {
		    if (getMultiplicity)
		    	conn += "*";
		    hashMapClassConnection.put(classShortName + "-" + getDependencies,
		    		conn);
		}
	}

	private String nextArgumentChange(String fields, boolean nextArgument, String scopeOfField, String fieldClassName,
			String fieldN) {
		if (nextArgument)
		    fields += "; ";
		fields += scopeOfField + " " + fieldN + " : " + fieldClassName;
		return fields;
	}

	private String changeScope(ArrayList<String> makeFieldPublic, String fieldScope, String fieldN) {
		if (fieldScope.equals("-") && makeFieldPublic.contains(fieldN.toLowerCase())) {
		    fieldScope = "+";
		}
		return fieldScope;
	}

	private String equalFieldName(BodyDeclaration bodyDeclaration, String fieldN) {
		if (fieldN.contains("="))
			fieldN = ((FieldDeclaration) bodyDeclaration).getChildrenNodes().get(1).toString()
		            .substring(0, ((FieldDeclaration) bodyDeclaration).getChildrenNodes().get(1)
		                    .toString().indexOf("=") - 1);
		return fieldN;
	}

	  
	  
	  
	  
	  private String nextParam(String methods, boolean nextParameter) {
			if (nextParameter)
			    methods += ";";
			return methods;
		}

		private void getset(final ArrayList<String> makeFieldPublic, final BodyDeclaration bodyDeclaration) {
			final String varName = ((MethodDeclaration) bodyDeclaration).getName().substring(3);
			makeFieldPublic.add(varName.toLowerCase());
		}
		
		
		

		private String  paramClassAddition2(String classShortName, String additions, final String paramClass) {
			if ((map.containsKey(paramClass) && !map.get(classShortName))) {
				
			    additions += "[" + classShortName + "] uses -.->";
			    
			    additions = paramClassAddition(additions, paramClass);
			}
			additions += ",";
			return additions;
		}
		
		
		
		
		private String paramClassAddition(String additions, String paramClass) {
			additions += map.get(paramClass) ? "[<<interface>>;" + paramClass + "]" : "[" + paramClass + "]";
			return additions;
		} 

		

	  }