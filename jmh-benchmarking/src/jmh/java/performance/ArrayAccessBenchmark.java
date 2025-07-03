package performance;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class ArrayAccessBenchmark {

    @Param({"100000"})
    private int size;

    private int[] array;
    private List<Integer> arrayList;

    @Setup(Level.Iteration)
    public void setup() {
        array = new int[size];
        arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            array[i] = i;
            arrayList.add(i);
        }
    }

    //@Benchmark
    public int accessArrayMiddle() {
        return array[size / 2];
    }

    //@Benchmark
    public int accessArrayListMiddle() {
        return arrayList.get(size / 2);
    }
}