

import java.awt.*;
import java.util.ArrayList;

public class BeadFinder {

    static double _Tau ;
    static Picture _Pic ;

    public BeadFinder(Picture picture , double tau){
        _Pic = picture;
        _Tau = tau;
    }
    public static int[][] noiseCleaner (){
        int [][] CleanedImage = new int[_Pic.width()][_Pic.height()];
        for (int i = 0 ; i < _Pic.width() ; i++){
            for (int j = 0 ; j < _Pic.height() ; j++){
                Color color = _Pic.get(i,j);
                if (color.getRed() < _Tau && color.getGreen() < _Tau && color.getBlue() < _Tau)    CleanedImage[i][j] = 1;
            }
        }
        return CleanedImage;
    }
    public static void blobFF(int[][] CleanedImage, Blob blob, int i , int j ){
        // stop conditions
        if (i == 640 || j == 480)        return;
        if (i == -1 || j == -1)         return;
        if (CleanedImage [i][j] == 1)        return;
// filling the 0 with 1
        CleanedImage [i][j] = 1;

        blob.add(i,j);

        blobFF(CleanedImage, blob, i+1,j);
        blobFF(CleanedImage, blob, i+1,j+1);
        blobFF(CleanedImage, blob, i+1,j-1);
        blobFF(CleanedImage, blob, i-1,j);
        blobFF(CleanedImage, blob, i-1,j-1);
        blobFF(CleanedImage, blob, i-1,j+1);
        blobFF(CleanedImage, blob, i,j+1);
        blobFF(CleanedImage, blob, i,j-1);

    }
    public static Blob[] getBeads (int min){
        int [][] CleanedImage = noiseCleaner();
        ArrayList<Blob> beads = new ArrayList<>();
        for (int i = 0 ; i < CleanedImage.length ; i++){
            for (int j = 0 ; j < CleanedImage[0].length ; j++){
                if (CleanedImage[i][j] == 0){
                    Blob a = new Blob();
                    blobFF(CleanedImage, a,i,j);
                    if (a.mass() >= min){
                        beads.add(a);
                    }
                }
            }
        }
        Blob[] result = new Blob[beads.size()];
        for (int i = 0 ; i < result.length ; i++){
            result [i] = beads.get(i);
        }
        return result;
    }
    public static void main (String[] args){
        int minMass = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        String path = args[2];
        Picture pic = new Picture (path);
        _Tau = tau;
        _Pic = pic;
        Blob[] result = getBeads(minMass);
        for (Blob i : result){
            System.out.println(i.toString());
        }
    }
//    D:\\Atomic-Nature-of-Matter-master\\Atomic-Nature-of-Matter-master\\Dataset\\run_1


}
