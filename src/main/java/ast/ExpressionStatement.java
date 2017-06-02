package ast;

import java.io.IOException;
import java.nio.file.Path;

public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        expression.resolveNames(scope);
    }
    
    @Override
    public void codeGeneration(Path path) throws IOException {
        expression.codeGeneration(path);
    }
}
