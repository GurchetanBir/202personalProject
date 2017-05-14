# 202personalProject
UML CLASS PARSER
To convert java code into UML class diagram 
Instructions

Requirements:
Two Arguments are required 

1. Path to the test class folder: this folder will also be the path to the output file in PNG format too.

2. Name of your output file : It can be anything like a single word string. 

Example of the command 
java    –jar    umlfinal.jar   “/users/chetansidhu/desktop/test15” chetan
This will generate:
chetan.png




	

Libraries and tools used

The project works in two phases:

1.	Parsing: This phase parses the source code and generate a grammar that can fed to UML generator to generate a Diagram.
For Parsing I used javaparser library.
This library helps access each unit of the source code and differentiate them according to class, Interface, method, constructor. And parses the relations between different units of code.
https://github.com/javaparser/javaparser

2.  Diagram Generating: Diagram is generated in this phase.
yUML  is used for generating the class diagram from the parsed code grammer. GenerateUMLDiagram.java sends a get request in which it appent the grammar created. And it returns back the diagram
http://yuml.me/


