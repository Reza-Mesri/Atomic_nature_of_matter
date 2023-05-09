

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class BeadTracker {
    public static void main(String [] args){

        int delta , mass , tau ;
        String path ;

        delta = Integer.parseInt(args[0]);
        mass = Integer.parseInt(args[1]);
        tau = Integer.parseInt(args[2]);
        path = args[3];

        double[] Distances = DistanceEstimator(delta,mass,tau,path);
        for (int i = 0 ; i < Distances.length ; i++){
            System.out.println(Distances[i]);
        }
    }
    public static double distance (double x0, double y0 , double x1, double y1){// this func calculates the distance between two points in the space
        double deltaX = Math.abs(x0-x1);
        double deltaY = Math.abs(y0-y1);
        return Math.sqrt(Math.pow(deltaX , 2) + Math.pow(deltaY , 2));
    }
    public static double [] DistanceEstimator (int delta  , int mass , int tau , String path){
        ArrayList<Double> Distance = new ArrayList<>();

        File folder = new File(path);
        String [] contents = folder.list();
        String directPath ;
        Blob [][] DataBase = new Blob [contents.length][];
        for (int i = 0 ; i < contents.length ; i ++){
            directPath = path + "\\" + contents[i];
            Picture pic = new Picture(directPath);
            BeadFinder a = new BeadFinder(pic,tau);
            DataBase [i] = a.getBeads(mass);
        }
        for (int i = 0 ; i < contents.length - 1 ;i++){
            for (int j = 0 ; j < DataBase[i].length ; j++){
                double a = Tracker(DataBase[i][j],DataBase[i+1],delta);
                if (a != -1){
                    Distance.add(a);
                }
            }
        }


        double [] result = new double[Distance.size()];
        for (int i = 0 ; i < result.length ; i++){
            result[i] = Double.parseDouble(String.format("%.4f",Distance.get(i)));
        }
        return result;
    }

    public static double Tracker (Blob blob, Blob [] nextFrame , int delta){
        double Distance = 0;
        ArrayList<Double> possibleBlobsDist = new ArrayList<>();
        for (int i = 0 ; i < nextFrame.length ; i ++){
            Distance = blob.distanceTo(nextFrame[i]);
            if (Distance <= delta){
                possibleBlobsDist.add(Distance);
            }
        }
        if (possibleBlobsDist.size() == 0)     return -1;
        Collections.sort(possibleBlobsDist);
        return possibleBlobsDist.get(0);

    }

}
