package performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class StringTwice {

    @Benchmark
    public String useStringLiteral() {
        String s = "hello";
        return s;
    }

    @Benchmark
    public String useNewString() {
        String s = new String("hello");
        return s;
    }
}
