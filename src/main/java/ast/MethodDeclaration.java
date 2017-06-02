package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

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
    public void codeGeneration(Path path) throws IOException {
        iterateModifiers(path, this.modifiers);
        emit(path, result.getNameType() + " " + methodname.value + "\r\n" + ONE_IDENT + "{\r\n");
        methodBody.codeGeneration(path);
        emit(path, ONE_IDENT + "}\r\n");
    }
}
