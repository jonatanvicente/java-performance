package tuning;

import java.io.*;
import java.util.*;


public class WordFrequencyAnalyzer {

    //Analiza un texto real, cuenta las palabras más frecuentes y las imprime ordenadas
    static final String FILE_PATH = "files/episode_IVb.txt";

    public static void main(String[] args) throws IOException {
        String path = WordFrequencyAnalyzer.class.getClassLoader().getResource(FILE_PATH).getPath();
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();

        long start = System.currentTimeMillis();
        analyzer.analyze(path);
        //analyzer.analyzeOptimized(path);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " ms");

    }

    public void analyzeOptimized(String filePath) throws IOException {
            Map<String, Integer> freqMap = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                reader.lines()
                        .flatMap(line -> Arrays.stream(line.split("\\W+")))
                        .filter(word -> !word.isEmpty())
                        .forEach(word -> freqMap.merge(word, 1, Integer::sum));
            }

            System.out.println("\nTop 10 words:");
            freqMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));

        }



    public void analyze(String filePath) throws IOException {
        long start = System.currentTimeMillis();

        List<String> lines = readAllLines(filePath);
        List<String> words = extractAllWords(lines);

        Map<String, Integer> freqMap = countFrequencies(words);

        System.out.println("\nTop 10 words:");
        freqMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));

        long end = System.currentTimeMillis();
        System.out.printf("%nFinished in %.2f seconds%n", (end - start) / 1000.0);
    }

    private List<String> readAllLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private List<String> extractAllWords(List<String> lines) {
        List<String> words = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\W+");
            words.addAll(Arrays.asList(parts));
        }
        return words;
    }

    private Map<String, Integer> countFrequencies(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty()) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }
}

/*

CON tuning de JVM:
    Finished in 0.58 seconds
    Elapsed time: 576 ms

 */

