package ast;

public class ElseIfStatement extends Statement{
    private Expression elseifExpression;
    private Statement elseifStatement;
    
    private LexicalScope scope;
    
    public ElseIfStatement(Expression elseifExpression, Statement elseifStatement) {
        this.elseifExpression = elseifExpression;
        this.elseifStatement = elseifStatement;
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        elseifExpression.resolveNames(scope);
        elseifStatement.resolveNames(scope);
    }
}
