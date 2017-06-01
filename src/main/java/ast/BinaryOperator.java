package ast;

public class BinaryOperator extends Node {
    private String operator;

    public BinaryOperator(String operator) {
        this.operator = operator;
    }
    
    public String getOperator() {
        return operator;
    }
}
