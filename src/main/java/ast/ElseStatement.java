package ast;

public class ElseStatement extends Statement{
    private Statement elseStatement;
    
    private LexicalScope scope;
    
    public ElseStatement(Statement elseStatement) {
        this.elseStatement = elseStatement;
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        elseStatement.resolveNames(scope);
    }

}
