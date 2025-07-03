package performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BenchmarkString {

    /*
        //jmh will report results for both values of the parameter
        @Param({"1","10000"})
        private int nStrings;
     */


    //@Benchmark
    public void testIntern(Blackhole bh) { //if the value of an operation isn't used, the compiler is free to optimize out the operation
        for (int i = 0; i < 10000; i++) {
            String s = new String ("String to intern " + i);
            String t = s.intern();
            //we make sure that the values are used by passint them to the consume method
            bh.consume(t);
        }
    }
}

/*
    Output analysis (result is saved to java-performance/jmh-benchmarking/build/results/jmh/results.txt, see copy at resources folder):

        - five warm-up iterations of 10 seconds each
        - five measurement iterations (also of 10 seconds each)
    Forks (five):
        - each time in a separate (newly forked) JVM in order to determine the repeatability of the results
        - each JVM needs to warm up and then measure the code (trial)
        - Score: testIntern() method executed 899 times per sec.

 */
