package ast;

import java.io.IOException;
import java.nio.file.Path;

public class VariableInitializer extends Node {
	private Expression rhs;
	
	public VariableInitializer(Expression rhs) {
		this.rhs = rhs;
	}
	@Override
	public void codeGeneration(Path path)throws IOException {
		rhs.codeGeneration(path);
	}
}
