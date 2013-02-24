/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roboticsimproc;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;
import roboticsimproc.lectures.CImage;

/**
 *
 * @author fallen
 */
public class ImProcUtils {

    public static boolean[][] extendObstacles(boolean[][] arr, int thinkness) {
        boolean[][] res = new boolean[arr.length][arr[0].length];
        int t = thinkness / 2;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = false;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j]) {
                    for (int i1 = -t; i1 < t; i1++) {
                        for (int j1 = -t; j1 < t; j1++) {
                            try {
                                res[i + i1][j + j1] = true;
                            } catch (IndexOutOfBoundsException ex) {
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    
    
    public static boolean[][] inversedThreshold(boolean[][] thresholded) {
        for (int i = 0; i < thresholded.length; i++) {
            for (int j = 0; j < thresholded[i].length; j++) {
                thresholded[i][j] = !thresholded[i][j];
            }
        }
        return thresholded;
    }

    public static Vector<Point> getFirstPoints(boolean[][] thresholded, int numberOfPoints) {
        Vector<Point> v = new Vector<Point>();
        for (int i = 0; i < thresholded.length; i++) {
            for (int j = 0; j < thresholded[i].length; j++) {
                if (numberOfPoints <= 0) {
                    return v;
                }
                if (thresholded[i][j]) {
                    v.add(new Point(i, j));
                    numberOfPoints--;
                }
            }
        }
        return v;
    }

    public static Vector<Point> getFirstRandomPoints(boolean[][] thresholded,
            int numberOfPoints) {
        Vector<Point> points = getFirstPoints(thresholded, thresholded.length*thresholded[0].length);
        double prob = ((double)numberOfPoints) / ((double)points.size());
        Vector<Point> res = new Vector<Point>();
        for (Point point : points) {
            if (new Random().nextInt(1000) < 1000*prob) {
                res.add(point);
            }
        }
        return res;
    }
}
