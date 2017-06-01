package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import cil.CILOption;

public class ClassDeclaration extends Node {
    private IdentifierNode classname;
    private List<String> modifiers;
    private ClassBody classBody;

    public ClassDeclaration(IdentifierNode name, List<String> modifiers, ClassBody classBody) {
        this.classname = name;
        this.modifiers = modifiers;
        this.classBody = classBody;
    }

    @Override
    public void resolveNames(LexicalScope scope) {
        classBody.resolveNames(scope);
    }

    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
    	Files.write(path, "".getBytes(), StandardOpenOption.CREATE);
        emit(path, ".assembly extern mscorlib{}\r\n.assembly extern JDK{}\r\n.assembly " + classname.value + "{}\r\n.class ");
        iterateModifiers(path, this.modifiers);       
        emit(path, classname.value + "." + classname.value + " extends [mscorlib]System.Object\r\n{\r\n");
        classBody.codeGeneration(path, cilOption);
        emit(path, "}\r\n");
    }
}
