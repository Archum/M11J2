package ast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import cil.CILOption;

public class ClassBody extends Node {
	private List<Declaration> declarations;
	
	private LexicalScope scope;
	
	public ClassBody(List<Declaration> methodDec) {
		this.declarations = methodDec;
		this.scope = new LexicalScope();
	}
	
	@Override
    public void resolveNames(LexicalScope scope) {
        // the scope from whom invoked this method is the parent scope
        this.scope.parentScope = scope;
        
        // add all declaration statement to the symbol table of method scope
        for(Declaration declaration : declarations) {
            this.scope.symbolTable.put(declaration.getName(), declaration);
        }
        
        for (Declaration declaration : declarations) {
            Node node = (Node) declaration;
            node.resolveNames(this.scope);
        }
    }
	
	@Override
	public void codeGeneration (Path path, CILOption cilOption) throws IOException {
		 for (Declaration declaration : declarations) {
	            Node node = (Node) declaration;
	            node.codeGeneration(path, cilOption);
		 }
	}
}
