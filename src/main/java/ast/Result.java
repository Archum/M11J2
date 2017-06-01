package ast;

public class Result extends Node {
	private Type type;

	public Result(Type type) {
		this.type = type;
	}
	
	public String getNameType() {
		
		return type.getNameType();
	}
}
