package performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ConcurrentHashMap;

public class BenchmarkStringSetup {

    private static int nStrings = 10000;
    private static ConcurrentHashMap<String,String> map;
    private static String[] strings;
/*
    Level.Trial - The setup is done once, when the benchmark code initializes.
    Level.Iteration - The setup is done before each iteration of the benchmark (each measurement cycle).
    Level.Invocation - The setup is done before each time the test method is executed.
 */
    //@Setup(Level.Iteration)
    public void setup() {
        strings = new String[nStrings];
        for (int i = 0; i < nStrings; i++) {
            strings[i] = makeRandomString();
        }
        map = new ConcurrentHashMap<>();
    }


    @Benchmark
    public void testIntern(Blackhole bh) {
        for (int i = 0; i < nStrings; i++) {
            String t = strings[i].intern();
            bh.consume(t);
        }
    }

    private static String makeRandomString() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}

