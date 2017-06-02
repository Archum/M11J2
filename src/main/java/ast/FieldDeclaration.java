package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FieldDeclaration extends Node implements Declaration {
    private List<String>  fieldmodifier;
    private Type type;
    private IdentifierNode identifiernode;
    
    public FieldDeclaration( List<String> fieldmodifier, Type type,IdentifierNode identifiernode, VariableInitializer varInit) 
    {
        this.fieldmodifier = fieldmodifier;
        this.type = type;
        this.identifiernode = identifiernode;
    }

	@Override
	public String getName() {
		return identifiernode.value;
	}
	
	@Override
    public void resolveNames(LexicalScope scope) {
        type.resolveNames(scope);
    }
    
    @Override
    public void codeGeneration(Path path) throws IOException {
        emit(path, ONE_IDENT + ".field");
        iterateModifiers(path, this.fieldmodifier);
        type.codeGeneration(path);
        identifiernode.codeGeneration(path);    
    }
}
