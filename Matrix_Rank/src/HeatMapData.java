import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.io.*;

import static java.lang.Math.toIntExact;

public class HeatMapData {
    public static ArrayList<ArrayList<Integer>> DATA = new ArrayList<>();

    // outputs arraylist of (a^T)(b), where a and b are row vectors
    public static int[] product(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> productList = new ArrayList<>();
        for (int bElem : b) {
            for (int aElem : a) {
                productList.add(aElem * bElem);
            }
        }
        int[] returnArray = new int[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            returnArray[i] = productList.get(i);
        }
        return returnArray;
    }

    // takes in dimension and rank, outputs random corresponding MatrixConstructor instance
    public static MatrixConstructor matrixGenerator(int dim, int rank) {
        int[] matrix = new int[dim * dim];

        for (int i = 0; i < rank; i++) {
            // generate the two random vectors
            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            Random rand = new Random();
            for (int j = 0; j < dim; j ++) {
                a.add(1 + rand.nextInt(10));
                b.add(1 + rand.nextInt(10));
            }

            //take the product of the vectors
            int[] productMatrix = product(a, b);

            //add to current matrix
            for (int k = 0; k < dim * dim; k ++){
                matrix[k] = matrix[k] + productMatrix[k];
            }
        }
        MatrixConstructor returnMatrix = new MatrixConstructor(dim, dim, matrix);
        return returnMatrix;
    }

    // generates necessary data
    public static void dataMaker(int maxDim, int maxRank) {
        for (int i = 0; i < maxRank; i ++) {
            for (int j = 0; j < maxDim; j ++) {
                if (i < j) {
                    // generate the matrix of rank i, of dim j
                    MatrixConstructor myMatrix = matrixGenerator(j, i);

                    //start timer
                    long start = System.currentTimeMillis();

                    //calculate rank
                    myMatrix.Rank();

                    //end timer
                    long end = System.currentTimeMillis();

                    //calculate time taken
                    int timeTaken = toIntExact(end - start);

                    //add to database, whose arrays are (rank, dimension, time)
                    ArrayList<Integer> generatedData = new ArrayList<>();
                    generatedData.add(i);
                    generatedData.add(j);
                    generatedData.add(timeTaken);
                    DATA.add(generatedData);
                }
            }
        }
    }




}
