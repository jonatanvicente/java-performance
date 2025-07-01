package performance;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class ListVsMapAccessBenchmark {

    @Param({"100000"})
    private int size;

    private List<Integer> arrayList;
    private Map<Integer, Integer> hashMap;

    @Setup(Level.Iteration)
    public void setup() {
        arrayList = new ArrayList<>(size);
        hashMap = new HashMap<>(size);

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            hashMap.put(i, i);
        }
    }

    @Benchmark
    public int accessArrayListMiddle() {
        return arrayList.get(size / 2);
    }

    @Benchmark
    public int accessHashMapMiddleKey() {
        return hashMap.get(size / 2);
    }
}
