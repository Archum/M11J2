package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MethodBody extends Node {
    private List<Statement> statements;

    private LexicalScope scope;

    public MethodBody(List<Statement> localVarDec) {
        this.statements = localVarDec;
        this.scope = new LexicalScope();
    }

    @Override
    public void resolveNames(LexicalScope scope) {
        this.scope.parentScope = scope;

        for (Statement statement : statements) {
            if (statement instanceof Declaration) {
                Declaration declaration = (Declaration) statement;
                this.scope.symbolTable.put(declaration.getName(), declaration);
            }
        }

        for (Statement statement : statements) {
            statement.resolveNames(this.scope);
        }
    }

	@Override
	public void codeGeneration(Path path) throws IOException {     
        for (Statement statement : statements) {
			if (Declaration.class.isInstance(statement)){	           
		           statement.codeGeneration(path);
		       }
	    }  
         
		for (Statement statement : statements) {
			if (!Declaration.class.isInstance(statement)){	          
		   	        statement.codeGeneration(path);
		      }
	    }
		emit(path, TWO_IDENT + "\r\n");   
	}
}
