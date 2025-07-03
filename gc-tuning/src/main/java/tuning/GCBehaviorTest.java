package tuning;

import java.util.ArrayList;
import java.util.List;

public class GCBehaviorTest {

    static final int _1KB = 1024;
    static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        List<byte[]> longLivedObjects = new ArrayList<>();

        // Llenamos Eden repetidamente
        for (int i = 0; i < 50; i++) {
            generateShortLivedObjects(); // Objetos que morirán pronto
            generateMediumLivedObjects(); // Objetos que vivirán 2-3 GCs

            if (i % 5 == 0) {
                // Estos objetos serán promovidos eventualmente
                longLivedObjects.add(new byte[2 * _1MB]);
            }

            Thread.sleep(100); // Para que VisualVM o JMC puedan trazar pausas
        }

        // Mantener la aplicación viva un poco más
        System.out.println("Finalizando bucle. Durmiendo para observar en JMC...");
        Thread.sleep(30000);
    }

    private static void generateShortLivedObjects() {
        for (int i = 0; i < 1000; i++) {
            byte[] temp = new byte[4 * _1KB]; // Objeto pequeño
        }
    }

    private static void generateMediumLivedObjects() {
        List<byte[]> tempList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            tempList.add(new byte[256 * _1KB]);
        }

        // Vivirán uno o dos ciclos de GC
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
    }
}
