package ast;

import java.io.IOException;
import java.nio.file.Path;

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

    }
    
    @Override
    public void codeGeneration(Path path) throws IOException {
        emit(path, TWO_IDENT);
        
    }
    public IdentifierNode getIdentifierNode() {
        return identifier;
    }
}
