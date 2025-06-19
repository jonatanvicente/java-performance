package performance.init;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, timeUnit = TimeUnit.MILLISECONDS, time = 3000)
public class BenchmarkTest {

    @Benchmark
    public void measureSomething(Blackhole blackhole) {
        blackhole.consume(50);
    }
}