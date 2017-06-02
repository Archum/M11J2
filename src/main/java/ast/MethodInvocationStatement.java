package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MethodInvocationStatement extends Statement {
    String invokeObject;
    String invokeClass;
    IdentifierNode invokeMethod;
    List<Expression> invokeParameter;

    public MethodInvocationStatement(List<String> name,
            IdentifierNode invokeMethod, List<Expression> invokeParameter) {
        this.invokeMethod = invokeMethod;
        this.invokeParameter = invokeParameter;
        this.invokeObject = name.get(name.size() - 1);
        this.invokeClass = name.get(0);
    }

    @Override
    public void resolveNames(LexicalScope scope) {

        for (Expression expression : invokeParameter) {
            expression.resolveNames(scope);
        }
    }

    @Override
    public void codeGeneration(Path path)
            throws IOException {

    }
}
