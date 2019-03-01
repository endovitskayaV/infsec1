import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static final double step = 2*Math.pow(10, -10);

    public static void main(String[] args) {
        test1();
       // test2();
    }

    private static void test1() {
        Main m = new Main();
        double r = 0;
        while (r + step < 1.0) {
            r += step;
            for (int t = 1; t < 100; t++) {
                m.generate(8 * t - 3, r);
                System.out.println();
            }
        }
    }

    private static void test2() {
        Main m = new Main();
        double r = 0;
        while (r + step < 1.0) {
            r += step;
            for (int t = 1; t < 10; t++) {
                m.generate(16 * t + 11, r);
                System.out.println();
            }
        }
    }

    private void generate(int m, double r) {
        System.out.print(" m=" + m);
        System.out.print(" r=" + r);
        List<Double> seq = new ArrayList<>();
        seq.add(r);
        for (int i = 0; i < 100_000; i++) {
            r = frac(m, r);
            seq.add(r);
        }

        //seq.forEach(System.out::print);

        len(seq);
        statistics(new HashSet<>(seq));
    }

    private double frac(int m, double r) {
        double a = m * r;
        return a - Math.floor(a);
    }

    private void len(List<Double> seq) {
        Set<Double> set = new HashSet<>();
        int i;
        int L = -1;
        for (i = 0; i < seq.size(); i++) {
            if (set.contains(seq.get(i))) {
                L = i;
                System.out.print(" L=" + L);
                break;
            }
            set.add(seq.get(i));
        }

    }

    private void statistics(Set<Double> seq) {
        double m = seq.stream().reduce((a, b) -> a + b).get() / seq.size();
        System.out.print(" mid=" + m);

        double d = seq.stream().mapToDouble(number -> Math.pow(m - number, 2)).sum() / seq.size();
        System.out.print(" d=" + d);

        final int intervalCount = 10;

        int[] numbersInIntervalCount = new int[intervalCount];

        for (double s : seq) {
            numbersInIntervalCount[(int) (s * intervalCount)]++;
        }

        double hi2 = 0;
        double averageFreq = ((double) seq.size()) / intervalCount;
        for (int i = 0; i < intervalCount; i++) {
            hi2 += Math.pow(numbersInIntervalCount[i] - averageFreq, 2);
        }
        hi2 /= averageFreq;

        System.out.print(" hi2=" + hi2);

    }
}