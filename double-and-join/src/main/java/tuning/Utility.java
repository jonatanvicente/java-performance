package tuning;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    static final int N = 10_000;

    public static void main(String[] args) {
        Utility util = new Utility();

        long start = System.currentTimeMillis();
        String output = util.doubleAndJoin(N);
        long end = System.currentTimeMillis();

        System.out.println("Output length: " + output.length());
        System.out.println("Elapsed time: " + (end - start) + " ms");
    }

    /**
     * Devuelve un String con los números del 0 a n-1 multiplicados por 2 y separados por coma.
     *
     */
    public String doubleAndJoin(int n) {
        String result = "";
        List<Integer> list = new ArrayList<>();

        // Generar lista de 0 a n-1
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        // Duplicar cada número y concatenar en un String
        for (int i = 0; i < list.size(); i++) {
            Integer value = list.get(i);
            list.set(i, value * 2);
            result += value * 2 + ",";  // Concatenación en bucle
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

}

