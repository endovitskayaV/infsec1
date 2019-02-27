import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    void generate(){
        generate1();
        generate2();
    }

    void generate1(){
        int t=10;
        int m=8*t+3;
        double r=0.11111111;
        List<Double> seq=new ArrayList<>();
        for(int i=0; i<1000; i++){
            seq.add(frac(m,r));
        }
        System.out.println("seq1= "+seq);
    }

    void generate2(){
        int q=10;
        int m=16*q+11;
        double r=0.11111111;
        List<Double> seq=new ArrayList<>();
        for(int i=0; i<1000; i++){
            seq.add(frac(m,r));
        }
        System.out.println("seq2= "+seq);
    }

    double frac(int m, double r){
        return 1;
    }
}
