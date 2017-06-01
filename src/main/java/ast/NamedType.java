package ast;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import cil.CIL;
import cil.CILOption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NamedType extends Type {
	private String nametype;

	public NamedType(String nametype) {
		this.nametype = nametype;
	}
	
	public String getNameType() {		
		return nametype;
	}

    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        if (nametype.equals("int")) {
        	emit(path, nametype + "32 ");
        }
        //TODO: zyx: need add more constion like double , float etc...
        //TODO: zyx : need add codeGeneration() method to the ElementType() for UnannReferenceType
    }
}