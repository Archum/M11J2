package cil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ast.BinaryOperator;

public class CIL {
    public static final String LDLOC = "ldloc.";
    
    public static final String STLOC = "stloc.";
    
    public static final String LDC_I4_S = "ldc.i4";
    
    public static final String LDSFLD ="ldsfld";
    
    public static final String CALLVIRT = "callvirt";
    
    public static final String ONE_IDENT = "   ";
    
    public static final String TWO_IDENT = "      ";
    
    public static final String THREE_IDENT = "          ";
    
    public static final String LABLE = "IL_";
    
    public static final String BRTRUE = "brtrue";
    
    public static final String BR = "br";
    
    public static final String CEQ = "ceq";
    
    public static String getBinaryOperator(BinaryOperator operator) {
        String cilOperator = "";
        
        switch(operator.getOperator()) {
        case "+" :
                cilOperator = "add";
                break;
        case "*":
                cilOperator = "mul";
                break;
        case ">":
                cilOperator = "cqt";
                break;
        case "<":
                cilOperator = "clt";
                break;
        // more operators mapping here
        }
        
        return cilOperator;
    }
    
    public static void comment(String comment, Path path) throws IOException {
        StringBuilder msg = new StringBuilder(CIL.TWO_IDENT);
        msg.append("// " + comment + "\r\n");
        Files.write(path, msg.toString().getBytes(), StandardOpenOption.APPEND);
    }
}
