package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class IntegerLiteralExpression extends Expression {
	private int value;
	
	public IntegerLiteralExpression(int value) {
		this.value = value;
	}
	
	@Override
    public void codeGeneration(Path path) throws IOException {

	}
}
