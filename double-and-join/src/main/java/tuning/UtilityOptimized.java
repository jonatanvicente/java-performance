package tuning;

public class UtilityOptimized {

    static final int N = 10_000;

    public static void main(String[] args) {
        Utility util = new Utility();

        long start = System.currentTimeMillis();
        String output = util.doubleAndJoin(N);
        long end = System.currentTimeMillis();

        System.out.println("Output length: " + output.length());
        System.out.println("Elapsed time: " + (end - start) + " ms");

    }

    public String doubleAndJoin(int n) {

        StringBuffer sb = new StringBuffer();

        // Generar lista de 0 a n-1
        for (int i = 0; i < n-1; i++) {
            sb.append(Integer.toString(i * 2));
            sb.append(",");
        }

        return sb.toString();
    }

}
/*
Output length: 54445
Elapsed time: 117 ms
 */




