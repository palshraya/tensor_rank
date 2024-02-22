import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    //row + column 0-indexed
    //assuming square matrix
    public ArrayList<ArrayList<Double>> ExcludingSubMatrix(ArrayList<ArrayList<Double>> matrix, int row, int col) {
        ArrayList<ArrayList<Double>> m = new ArrayList(matrix);
        m.remove(row);
        for (int i = 0; i < m.size(); i++) {
            ArrayList<Double> toReplace = m.get(i);
            toReplace.remove(col);
            m.set(i, toReplace);
        }
        return m;
    }

    //assuming square matrix
    public double Determinant(ArrayList<ArrayList<Double>> matrix){
       double determinant = 0.0;
       if (matrix.size() == 1){
           determinant = determinant + matrix.get(0).get(0);
       }
       for (int i = 0; i < matrix.size(); i++){
           // cofactor expansion
           determinant = determinant + (matrix.get(0).get(i) * Determinant(ExcludingSubMatrix(matrix, 0, i)));
       }
       return determinant;
    }

//    public static int rankCalculator(ArrayList<Double> dataMatrix) {
//
//    }

}



