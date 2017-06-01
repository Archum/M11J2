package ast;

public class ArrayType extends Type {
	private ElementType elementtype;

	public ArrayType(ElementType elementtype) {
		this.elementtype = elementtype;
	}
	
	
}
