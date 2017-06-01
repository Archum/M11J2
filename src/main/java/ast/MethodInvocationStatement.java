package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import cil.CIL;
import cil.CILOption;

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
    public void codeGeneration(Path path, CILOption cilOption)
            throws IOException {
        emit(path,  CIL.LDSFLD + CIL.THREE_IDENT
                + "class [JDK]java.lang." + invokeObject + " [JDK]java.lang."
                + invokeClass + "::" + invokeObject + "\r\n");
        //emit(path, CIL.TWO_IDENT + CIL.LDLOC + "\r\n");
        invokeParameter.get(0).codeGeneration(path, cilOption);
        emit(path, CIL.TWO_IDENT + CIL.CALLVIRT + CIL.THREE_IDENT
                + "instance void [JDK]java.lang." + invokeObject + "::"
                + invokeMethod.value + "(int32)" + "\r\n");
    }
}
