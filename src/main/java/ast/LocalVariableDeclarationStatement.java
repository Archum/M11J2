package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
    public void codeGeneration(Path path) throws IOException {

        type.codeGeneration(path);
        emit(path, this.getName());

   
    }
}
