package ast;

import java.util.ArrayList;
import java.util.List;

public class ASTDriver {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
    	ArrayList<String> modifiers = new ArrayList<String>();
    	modifiers.add("public");
    	modifiers.add("static");
    	
    	ElementType elementtype = new ElementType(new IdentifierNode ("String"));
    	Type atype = new ArrayType(elementtype);
    	List<Parameter> parameters = new ArrayList<Parameter>();
    	Parameter parameter = new Parameter(atype, new IdentifierNode("args"));
    	parameters.add(parameter);
    	
    	IntegerLiteralExpression expression = new IntegerLiteralExpression(42);
    	VariableInitializer varInit = new VariableInitializer(expression);
    	IdentifierNode varDecId = new IdentifierNode("x");
    	Type itype = new NamedType("int");
    	List <Statement> statements = new ArrayList<>();
    	LocalVariableDeclarationStatement localVarDec = new LocalVariableDeclarationStatement(itype, varDecId, varInit);
    	statements.add (localVarDec);
    	MethodBody methodBod = new MethodBody(statements);
    	
    	Type type = new NamedType("void");
    	Result result = new Result(type);
    	MethodDeclaration methodDec = new MethodDeclaration(modifiers, result,new IdentifierNode("main"), parameters, methodBod);
    	List<Declaration> declarationlist = new ArrayList<Declaration>();
    	declarationlist.add(methodDec);
    	ClassBody classBody = new ClassBody(declarationlist);
    	
    	List<String> modifiersForClass = new ArrayList<String>();
    	modifiersForClass.add("public");
    	
        Node root = new ClassDeclaration(new IdentifierNode("Example"), modifiersForClass, classBody);
        root.printNodeReflection(0);
    }
}
