

import java.util.*;

public class Avogadro {
    public static void main (String [] args){
        ArrayList<Double> blobsDistance = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            blobsDistance.add(input.nextDouble());
        }
        double [] result = avo(blobsDistance);

        System.out.println("boltzmann constant is : " + ScientificNotation(result[1],4));
        System.out.println("NA is : " + ScientificNotation(result[0],4));
    }
    public static String ScientificNotation(double a , int DecimalDigit){
        int power = (int)Math.log10(a);
        if (power <= 0) power--;
        double nomad = a /  Math.pow(10,power) ;
        String result = String.format("%."+ DecimalDigit + "f",nomad) + "e" + power;
        return result;
    }
    public static double[] avo ( ArrayList<Double> Distance) {
        double delta_t = 0.5;

        double sum = 0;
        for (int i = 0; i < Distance.size(); i++) {
            Distance.set(i,Distance.get(i) * 0.175 * 0.000001);
        }
        for (double i : Distance) {
            sum += Math.pow(i, 2);
        }
        double D = sum / ( Distance.size() * 4 * delta_t);

        double n = 9.135 * 0.0001;
        double P = 0.5 * 0.000001;
        double T = 297;
        double k = (6 * Math.PI * D * n * P) / T;
        double R = 8.31457;
        double AvoNum = R / k;
        double[] result = {AvoNum,k};
        return result;
    }
}
