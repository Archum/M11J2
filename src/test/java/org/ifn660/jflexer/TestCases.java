package org.ifn660.jflexer;

import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestCases {

    @Test
    public void testPass() throws Exception {
        String[] filenames = {"TestPass1.java"};

        final int expectedPass = filenames.length;
        int actualPass = 0;
        System.out.println("%%%%%%%%%%:"+expectedPass);

        for (String filename : filenames) {
            System.out.println("File " + filename);
            parser p = new parser(new Lexer(new FileReader(
                    "resources/test/" + filename)));
            p.parse();
            actualPass++;
            System.out.println("########:"+actualPass);
        }
        System.out.println("%%%%%%%%%%:"+actualPass);
        Assert.assertEquals(expectedPass, actualPass);
    }

    @Test
    public void testFail() {
        String[] filenames = { "TestCompilationUnitFail.java",
                "TestCompilationUnitFail2.java", "TestDecimalNumeralFail.java",
                "TestFail1.java", "TestFail2.java", "TestFail3.java",
                "TestFail4.java", "TestFail5.java" };

        final int expectedFail = filenames.length;
        int actualFail = 0;

        for (String filename : filenames) {
            try {
                parser p = new parser(new Lexer(new FileReader(
                        "resources/test/" + filename)));
                p.parse();
                Assert.fail("Grammar fail with " + filename);
            } catch (Exception e) {
                actualFail++;
            }
        }

        Assert.assertEquals(expectedFail, actualFail);
    }
}
