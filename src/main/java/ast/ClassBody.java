package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ClassBody extends Node {
	private List<Declaration> declarations;
	
	private LexicalScope scope;
	
	public ClassBody(List<Declaration> methodDec) {
		this.declarations = methodDec;
		this.scope = new LexicalScope();
	}
	
	@Override
    public void resolveNames(LexicalScope scope) {
        this.scope.parentScope = scope;

        for(Declaration declaration : declarations) {
            this.scope.symbolTable.put(declaration.getName(), declaration);
        }
        
        for (Declaration declaration : declarations) {
            Node node = (Node) declaration;
            node.resolveNames(this.scope);
        }
    }
	
	@Override
	public void codeGeneration (Path path) throws IOException {
		 for (Declaration declaration : declarations) {
	            Node node = (Node) declaration;
	            node.codeGeneration(path);
		 }
	}
}
