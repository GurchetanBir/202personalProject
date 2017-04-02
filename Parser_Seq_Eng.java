import java.util.*;
import java.io.*;

import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.*;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class ParserSE{

    String plantUMLCode;
    final String fun;
    final String op;
    final String ic;
    final String ip;

    ArrayList<CompilationUnit> na_array;
    HashMap<String, ArrayList<MethodCallExpr>> map_meth_call;
    HashMap<String, String> class_meth_map;


    ParserSE(String ip, String ic, String fun,String op) {
        class_meth_map = new HashMap<String, String>();
        map_meth_call = new HashMap<String, ArrayList<MethodCallExpr>>();
        this.i_function = i_function;
        this.o_path = i_path + "\\" + o_file + ".png";
        this.i_path = i_path;
        this.i_class = i_class;
        puml_code = "@startuml\n";
    }
}