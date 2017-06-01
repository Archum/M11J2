package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import cil.CIL;
import cil.CILOption;

public class IdentifierNode extends Node {
	public String value;

	public IdentifierNode(String value) {
		this.value = value;
	}
	@Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        StringBuilder msg = new StringBuilder(CIL.ONE_IDENT);
        
        
            //msg.append(CIL.STLOC + declaration.getCilLocalVarIndex() + "\r\n");
          //  msg.append(CIL.TWO_IDENT);
            msg.append(value+ "\r\n");
        
        Files.write(path, msg.toString().getBytes(), StandardOpenOption.APPEND);
    }
	

}
