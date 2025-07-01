package performance;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class HashMapLoadBenchmark {

    @Param({"100000"})
    private int size;

    @Benchmark
    @Threads(3)
    public HashMap<Integer, Integer> loadHashMap() {
        HashMap<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        return map;
    }
}
