package tuning;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class WordAnalyzerBenchmark {

    WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
    String FILE = "files/episode_IVb.txt";
    String FILE_PATH = "/home/Jonatan/projects/Java/java-performance/word-analyzer/src/main/resources/files/episode_IVb.txt";
    //String FILE_PATH = WordAnalyzerBenchmark.class.getClassLoader().getResource(FILE).getPath();
    //String FILE_PATH = getClass().getClassLoader().getResourceAsStream(FILE).toString();
    //String FILE_PATH = "./files/episode_IVa.txt";


    @Benchmark
    public void wordFrequencyPerformance() throws IOException {
        analyzer.analyze(FILE_PATH);
    }

    @Benchmark
    public void wordFrequencyPerformanceOptimized() throws IOException {
        analyzer.analyzeOptimized(FILE_PATH);

    }

}


/*

    SIN tuning de JVM:
Benchmark                                                Mode  Cnt    Score   Error  Units
WordAnalyzerBenchmark.wordFrequencyPerformance           avgt       305.504          ms/op
WordAnalyzerBenchmark.wordFrequencyPerformanceOptimized  avgt       267.048          ms/op


 */