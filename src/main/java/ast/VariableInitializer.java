package ast;

import java.io.IOException;
import java.nio.file.Path;

import cil.CIL;
import cil.CILOption;

public class VariableInitializer extends Node {
	private Expression rhs;
	
	public VariableInitializer(Expression rhs) {
		this.rhs = rhs;
	}
	@Override
	public void codeGeneration(Path path, CILOption cilOption)throws IOException {
		rhs.codeGeneration(path, cilOption);
	}
}
