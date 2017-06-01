package ast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import cil.CIL;
import cil.CILOption;

public class MethodDeclaration extends Node implements Declaration {
    private List<String> modifiers;
    private Result result;
    private IdentifierNode methodname;
    private List<Parameter> parameters;
    private MethodBody methodBody;

    public MethodDeclaration(List<String> modifiers, Result result, IdentifierNode name, List<Parameter> parameters,
            MethodBody methodBody) {
        this.modifiers = modifiers;
        this.result = result;
        this.methodname = name;
        this.parameters = parameters;
        this.methodBody = methodBody;
    }

    @Override
    public void resolveNames(LexicalScope scope) {
        methodBody.resolveNames(scope);
    }

    @Override
    public String getName() {
        return methodname.value;
    }

    @Override
    public void codeGeneration(Path path, CILOption cilOption) throws IOException {
        emit(path, CIL.ONE_IDENT + ".method ");
        iterateModifiers(path, this.modifiers);
        emit(path, result.getNameType() + " " + methodname.value + "(string[] args) \r\n" + CIL.ONE_IDENT + "{\r\n");
        methodBody.codeGeneration(path, cilOption);
        emit(path, CIL.ONE_IDENT + "}\r\n");
    }

    @Override
    public int getCilLocalVarIndex() {
        return 0;
    }
}
