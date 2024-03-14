import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HeatMapData {

    // outputs arraylist of (a^T)(b), where a and b are row vectors
    public static double[] product(ArrayList<Double> a, ArrayList<Double> b) {
        ArrayList<Double> productList = new ArrayList<>();
        for (double bElem : b) {
            for (double aElem : a) {
                productList.add(aElem * bElem);
            }
        }
        double[] returnArray = new double[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            returnArray[i] = productList.get(i);
        }
        return returnArray;
    }

    // takes in dimension and rank, outputs random corresponding MatrixConstructor instance
    public static MatrixConstructor matrixGenerator(int dim, int rank) {
        double[] matrix = new double[dim * dim];

        for (int i = 0; i < rank; i++) {
            // generate the two random vectors
            ArrayList<Double> a = new ArrayList<>();
            ArrayList<Double> b = new ArrayList<>();
            for (int j = 0; j < dim * dim; j ++) {
                a.add(ThreadLocalRandom.current().nextDouble(0, 10));
                b.add(ThreadLocalRandom.current().nextDouble(0, 10));
            }

            //take the product of the vectors
            double[] productMatrix = product(a, b);

            //add to current matrix
            for (int k = 0; k < dim * dim; k ++){
                matrix[k] = matrix[k] + (Double) productMatrix[k];
            }
        }
        MatrixConstructor returnMatrix = new MatrixConstructor(dim, dim, matrix);
        return returnMatrix;
    }
}
