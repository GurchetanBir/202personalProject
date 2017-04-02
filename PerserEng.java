import java.lang.*;
import java.util.*;
import java.io.*;


import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.*;

public class ParserEng {
    ArrayList<CompilationUnit> na_arr;
    String code_yuml;
    HashMap<String, Boolean> string_map;
    final String o_path;
    HashMap<String, String> conn_map;
    final String i_path;

    ParserEng(String i_file, String o_file) {
        conn_map = new HashMap<String, String>();
        string_map = new HashMap<String, Boolean>();
        code_yuml = "";
        this.i_path = i_path;
        this.o_path = i_path + "\\" + o_file + ".png";
    }
    }