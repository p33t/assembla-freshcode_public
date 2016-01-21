/*
 Notes:
 - http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
 - Interfaces with 1 method are 'functional interfaces' (formerly SAM)
 - Method reference String::hashCode
 - java.util.function for various function interfaces
 - Collection.stream() to facilitate extra ops
 - Collectors for getting collection back from stream
 - Parallel streams
 */
package pkg;

import junit.framework.TestCase;

import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;

public class Java8 extends TestCase {
    public void testLambdaExpression() {
        Runnable r = () -> System.out.println("Run");
        r.run();
    }

    public void testLambdaMethodReference() {
        List<PrintStream> l = asList(System.out, System.err);
        l.forEach(PrintStream::println);
    }

    public void testStreams() {
        List<String> l = asList("1", "2", "3", "11", "22", "33");
        int i = l.stream()
                .filter(s -> s.length() == 1)
                .mapToInt(String::hashCode)
                .sum();
        System.out.println(i);
    }

}
