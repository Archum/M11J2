package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class IdentifierNode extends Node {
	public String value;

	public IdentifierNode(String value) {
		this.value = value;
	}
	@Override
    public void codeGeneration(Path path) throws IOException {
        StringBuilder msg = new StringBuilder(ONE_IDENT);
        msg.append(value+ "\r\n");
        
        Files.write(path, msg.toString().getBytes(), StandardOpenOption.APPEND);
    }
	

}
