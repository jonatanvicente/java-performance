package performance;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class ClassLoadingBenchmark {

    @Param({"java.util.ArrayList"})
    private String className;

    //@Benchmark
    public Class<?> loadClass() throws ClassNotFoundException {
        return Class.forName(className, false, getClass().getClassLoader());
    }
}