package performance;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(2)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
public class BoxingBenchmark {

    private static final int SIZE = 1000;
    private int[] primitiveArray = new int[SIZE];
    private Integer[] wrapperArray = new Integer[SIZE];

    @Setup
    public void setup() {
        for (int i = 0; i < SIZE; i++) {
            primitiveArray[i] = i;
            wrapperArray[i] = i;
        }
    }

    //@Benchmark
    public int sumPrimitive() {
        int sum = 0;
        for (int value : primitiveArray) {
            sum += value;
        }
        return sum;
    }

    //@Benchmark
    public int sumWrapper() {
        int sum = 0;
        for (Integer value : wrapperArray) {
            sum += value; // unboxing happens here  :(
        }
        return sum;
    }
}

//  Output: sumPrimitive is significantly faster than sumWrapper