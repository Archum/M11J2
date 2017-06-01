

import java.io.FileReader;

public class Test {

	public static void main(String[] args) {
        try {
            parser p = new parser(new Lexer(new FileReader("src/main/java/HelloWorld.java")));
            p.parse();
            p.root.resolveNames(null);
            p.root.printNodeReflection(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
