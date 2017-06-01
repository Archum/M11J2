package ast;

import java.io.IOException;
import java.nio.file.Path;

import cil.CIL;
import cil.CILOption;

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
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        //CIL.comment("Expression statement x=local[0], y=local[1], y = x + 4;", path);
        expression.codeGeneration(path, cilOption);
    }
}
