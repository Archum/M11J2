package ast;

import java.util.HashMap;
import java.util.Map;

public class LexicalScope {
    LexicalScope parentScope;
    Map<String, Declaration> symbolTable;
    
    public LexicalScope() {
        symbolTable = new HashMap<>();
    }
    
    public Declaration resolveHere(String symbol) {
        return symbolTable.get(symbol);
    }
    
    public Declaration resolve(String symbol) {
        Declaration local = resolveHere(symbol);
        if (local != null) {
            return local;
        } else if (parentScope != null) {
            return parentScope.resolve(symbol);
        } else {
            return null;
        }
    }
}
