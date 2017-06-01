package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.security.cert.PKIXRevocationChecker.Option;

import cil.CILOption;

public class AssignmentExpression extends Expression {
    private IdentifierExpression leftHandSide;
    private AssignmentOperator assignmentOperator;
    private Expression expression;
    
    public AssignmentExpression(IdentifierExpression leftHandSide, AssignmentOperator assignmentOperator,
            Expression expression) {
        this.leftHandSide = leftHandSide;
        this.assignmentOperator = assignmentOperator;
        this.expression = expression;
    }
    
    @Override
    public void resolveNames(LexicalScope scope) {
        leftHandSide.resolveNames(scope);
        expression.resolveNames(scope);
    }
    
    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        expression.codeGeneration(path, cilOption);
        leftHandSide.codeGeneration(path, CILOption.LEFT_HAND_SIDE);
    }
}
