package ast;

public class Parameter extends Node{
    private Type type;
    private IdentifierNode name;
	
    public Parameter(Type type, IdentifierNode name) {
		this.type = type;
		this.name = name;
	}
    
    
    
}
