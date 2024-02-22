import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class MatrixConstructor {
    public int row;
    public int col;
    public ArrayList<ArrayList<Double>> values;
    public static HashMap<ArrayList<Integer>, Double> determinantTracker = new HashMap<>();
    public static ArrayList<Integer> withoutIndices;

    //assumes correct dimensions are passed in
    public MatrixConstructor(int r, int c, double[] v) {
        this.row = r;
        this.col = c;
        determinantTracker.clear(); // janky
        this.values = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < this.row; i++) {
            ArrayList<Double> rowvals = new ArrayList<Double>();
            for (int j = 0; j < this.col; j++) {
                rowvals.add(v[j + (i * this.row)]);
            }
            this.values.add(rowvals);
        }
    }

    public MatrixConstructor(int r, int c, ArrayList<ArrayList<Double>> v) {
        this.row = r;
        this.col = c;
        this.values = v;
        determinantTracker.clear();
    }

    // creates array values for N x N identity matrix
    public static double[] NbyNIdentity(int N) {
        double[] elems = new double[N * N];
        for (int i = 0; i < N; i++) {
            elems[i * N + i] = 1.0;
        }
        return elems;
    }

    // returns a MatrixConstructor instance whose values are elements of original array w/o specified row and column
    public MatrixConstructor ExcludingSubMatrix(int row, int col) {
        ArrayList<ArrayList<Double>> m = new ArrayList();
        for (ArrayList<Double> rowItem : this.values){
            m.add((ArrayList<Double>) rowItem.clone()); //create deepcopy of list
        }
        m.remove(row);
        for (int i = 0; i < m.size(); i++) {
            ArrayList<Double> toReplace = m.get(i);
            toReplace.remove(col);
            m.set(i, toReplace);
        }
        MatrixConstructor returnMatrix = new MatrixConstructor(this.row - 1, this.col - 1, m);
        return returnMatrix;
    }

    // calculates determinant of matrix, assumes matrix is square
    public double Determinant() {
        double determinant = 0.0;
        if (this.values.size() == 1) {
            determinant = determinant + this.values.get(0).get(0);

        }
        else{
            for (int i = 0; i < this.values.size(); i++) {
                // cofactor expansion --> runtime analysis: https://informatika.stei.itb.ac.id/~rinaldi.munir/Matdis/2016-2017/Makalah2016/Makalah-Matdis-2016-051.pdf
                MatrixConstructor subMatrix = ExcludingSubMatrix(0, i);
                withoutIndices.add(i);
                double subMatrixDet = subMatrix.Determinant();

                // myKey = withoutIndices

                determinant = determinant + (Math.pow(-1, i%2) * (this.values.get(0).get(i) * subMatrixDet));
            }
        }
        determinantTracker.put(withoutIndices, determinant);
        withoutIndices.remove(withoutIndices.size()-1);
        return determinant;
    }

    // calculates rank of matrix, assumes matrix is square
    public int Rank(){
        if (this.Determinant() != 0.0){
            return this.row;
        }

    }

}
