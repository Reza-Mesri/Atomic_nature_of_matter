
import java.util.Scanner;
import java.util.ArrayList;

public class Blob {
    ArrayList<Integer> BlobPixel_X = new ArrayList<>();
    ArrayList<Integer> BlobPixel_Y = new ArrayList<>();

    public void add (int x , int y){
        BlobPixel_X.add(x);
        BlobPixel_Y.add(y);
    }
    public int mass(){
        return BlobPixel_X.size();
    }
    public double[] centerOfMass (){
        double sumOfMass_X = 0;
        double sumOfMass_Y = 0;
        for (int i = 0; i < BlobPixel_X.size() ; i++){
            sumOfMass_X += BlobPixel_X.get(i);
            sumOfMass_Y += BlobPixel_Y.get(i);
        }
        double [] result = new double[2];
        result [0] =  sumOfMass_X / (double)BlobPixel_X.size();
        result [1] =  sumOfMass_Y / (double)BlobPixel_X.size();
        return result;
    }
    public double distanceTo (Blob that){
        double[] centerCoordinates = centerOfMass();
        double[] centerCoordinatesOfThat = that.centerOfMass();
        double delta_X = Math.abs(centerCoordinates[0] - centerCoordinatesOfThat[0]);
        double delta_Y = Math.abs(centerCoordinates[1] - centerCoordinatesOfThat[1]);
        double result = Math.sqrt(Math.pow(delta_X,2) + Math.pow(delta_Y,2));
        result = Double.parseDouble(String.format("%.4f",result));
        return result;
    }
    public String toString(){
        double[] centerCoordinates = centerOfMass();
        int mass = BlobPixel_X.size();

        String Cx = String.format("%8.4f",centerCoordinates[0]);
        String Cy = String.format("%8.4f",centerCoordinates[1]);
        String blob = mass + "  " + "(" + Cx + ", " + Cy + ")";
        return blob;
    }
    public static void BlobInput (Blob blob , Scanner input){
        System.out.print("How many pixels is the blob :");
        int PixelNumBlob_1 = input.nextInt();
        System.out.println("Please enter pixels' coordinates [x y]:");
        for (int i = 0 ; i < PixelNumBlob_1 ; i++){
            blob.add(input.nextInt(),input.nextInt());
        }
    }
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        Blob blob_1 = new Blob();
        System.out.println("First blob ");
        BlobInput(blob_1,input);

        Blob blob_2 = new Blob();
        System.out.println("Second blob ");
        BlobInput(blob_2,input);

        System.out.println(" 1st blob   :" + blob_1.toString());
        System.out.println("2end blob   :" + blob_2.toString());
        System.out.println("Distance between to blob is :" + blob_1.distanceTo(blob_2));
    }
}
