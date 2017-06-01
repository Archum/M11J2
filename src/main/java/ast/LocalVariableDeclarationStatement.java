package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import cil.CIL;
import cil.CILOption;

public class LocalVariableDeclarationStatement extends Statement implements Declaration {
	private Type type;
	private IdentifierNode identifiernode;
    private VariableInitializer varInit;
    
    int cilLocalVarIndex;
    
    public LocalVariableDeclarationStatement(Type type, IdentifierNode identifiernode, VariableInitializer varInit) {
        this.identifiernode = identifiernode;
        this.varInit = varInit;
        this.type = type;
        
        this.cilLocalVarIndex = INDEX_COUNT++; 
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        type.resolveNames(scope);
    }

    @Override
    public String getName() {
        return identifiernode.value;
    }
    
    @Override
    public int getCilLocalVarIndex() {
        return this.cilLocalVarIndex;
    }
    
    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {

        if (cilOption == CILOption.DECLARE) {
        	String str = String.valueOf(this.getCilLocalVarIndex());      	
            emit(path, "[" + str + "] ");

            type.codeGeneration(path, cilOption);
            emit(path, this.getName());
        } else {
            if (varInit != null) {
            	varInit.codeGeneration(path, cilOption);
            	emit(path, CIL.TWO_IDENT + CIL.STLOC);
            	String str = String.valueOf(this.getCilLocalVarIndex());
            	emit(path, str + "\r\n");
            }
        }
    }
}
