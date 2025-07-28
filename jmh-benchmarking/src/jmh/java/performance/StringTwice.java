package performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class StringTwice {


    //Creates only one String object in the pool
    @Benchmark
    public String useStringLiteral() {
        String s = "hello";
        return s;
    }

    //Creates two String objects: one in the pool and one on the heap
    @Benchmark
    public String useNewString() {
        String s = new String("hello");
        return s;
    }
}
