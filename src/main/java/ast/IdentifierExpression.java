package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import cil.CIL;
import cil.CILOption;

public class IdentifierExpression extends Expression {
    private IdentifierNode identifier;
    
    private Declaration declaration;
    
    public IdentifierExpression(IdentifierNode identifierNode) {
        this.identifier = identifierNode;
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        if (scope != null) {
            declaration = scope.resolve(identifier.value);
        }

        if (declaration == null) {
            System.err.println("Error: Undeclared identifier " + identifier.value);
            throw new RuntimeException("Name Resolution error");
        }
    }
    
    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        emit(path, CIL.TWO_IDENT);
        
        if (cilOption == CILOption.LEFT_HAND_SIDE) {
            
            emit(path, CIL.STLOC + declaration.getCilLocalVarIndex() + "\r\n");
            emit(path, CIL.TWO_IDENT);
        } else {
        	emit(path, CIL.LDLOC + declaration.getCilLocalVarIndex() + "\r\n");
        }
    }
    public IdentifierNode getIdentifierNode() {
        return identifier;
    }
}
