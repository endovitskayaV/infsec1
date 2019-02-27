import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    int maxL = Integer.MIN_VALUE;
    int minL = Integer.MAX_VALUE;
    int maxl = Integer.MIN_VALUE;
    int minl = Integer.MAX_VALUE;
   static final  double step=Math.pow(2, -5);

    public static void main(String[] args) {
        Main m = new Main();
        double r = 0;
        while (r + step < 1.0) {
            r += step;
            for (int t = 1; t < 50; t++) {
                System.out.println("r="+r);
                System.out.println("t="+t);
                m.generate(8*t-3, r);
            }
        }
        System.out.println("minL=" + m.minL);
        System.out.println("maxL=" + m.maxL);
        System.out.println("minl=" + m.minl);
        System.out.println("maxl=" + m.maxl);
    }

    private void generate(int m, double r) {
        //System.out.println("m=" + m);
        //System.out.println("r=" + r);
        List<Double> seq = new ArrayList<>();
        seq.add(r);
        for (int i = 0; i < 100_000; i++) {
            r = frac(m, r);
            seq.add(r);
        }

        //seq.forEach(System.out::println);

        len(seq);
        statistics(new HashSet<>(seq));
    }

    private double frac(int m, double r) {
        double a = m * r;
        return a - Math.floor(a);
    }

    void len(List<Double> seq) {
        Set<Double> set = new HashSet<>();
        int i;
        int L;
        for (i = 0; i < seq.size(); i++) {
            if (set.contains(seq.get(i))) {
                L = i;
                      System.out.println("L=" + L);
                if (L < minL) {
                    minL = L;
                }
                if (L > maxL) {
                    maxL = L;
                }
                break;
            }
            set.add(seq.get(i));
        }

        L = i;
        int i1 = (int) (L * 95.0 / 100.0);
        double start = seq.get(i1);
        for (i = i1 + 1; i < seq.size(); i++) {
            if (seq.get(i).equals(start)) {
                int l = i - i1;
                System.out.println("l=" + l);
                if (l < minl) {
                    minl = l;
                }
                if (l > maxl) {
                    maxl = l;
                }
                break;
            }
        }
    }

    private void statistics(Set<Double> seq) {
        double m = seq.stream().reduce((a, b) -> a + b).get() / seq.size();
        System.out.println("m=" + m);

        double d = seq.stream().mapToDouble(number -> Math.pow(m - number, 2)).sum() / seq.size();
        System.out.println("d=" + d);

        final int intervalCount = 10;

        int[] numbersInIntervalCount = new int[intervalCount];

        for (double s: seq)
        {
            numbersInIntervalCount[(int) (s* intervalCount)]++;
        }

        double hi2 = 0;
        double averageFreq = ((double)seq.size())/intervalCount;
        for (int i = 0; i < intervalCount; i++) {
            hi2 += Math.pow(numbersInIntervalCount[i] - averageFreq, 2);
        }
        hi2 /= averageFreq;

        System.out.println("hi2="+hi2);

    }
}