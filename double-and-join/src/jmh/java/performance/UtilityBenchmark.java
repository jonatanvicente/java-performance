package performance;

import org.openjdk.jmh.annotations.*;
import tuning.Utility;
import tuning.UtilityOptimized;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class UtilityBenchmark {

    Utility util = new Utility();
    UtilityOptimized utilOpt = new UtilityOptimized();

    @Benchmark
    public void doubleAndJoinPerformance() {
        util.doubleAndJoin(10_000);
    }

    @Benchmark
    public void doubleAndJoinOptimized() {
        utilOpt.doubleAndJoin(10_000);
    }

}
/*
Benchmark                                  Mode  Cnt   Score   Error  Units
UtilityBenchmark.doubleAndJoinOptimized    avgt        0.195          ms/op
UtilityBenchmark.doubleAndJoinPerformance  avgt       23.846          ms/op

*/

