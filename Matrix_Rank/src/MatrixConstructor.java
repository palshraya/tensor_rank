import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.util.List;
import java.util.stream.IntStream;

//write toString method!
public class MatrixConstructor {
    public int row;
    public int col;
    public ArrayList<ArrayList<Double>> values;
    public static HashMap<ArrayList<Integer>, Double> determinantTracker = new HashMap<>(); //TreeMap, fix array comparable method
    public static ArrayList<Integer> withoutIndices = new ArrayList<>();

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
        for (ArrayList<Double> rowItem : this.values) {
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

        } else {
            for (int i = 0; i < this.values.size(); i++) {
                // cofactor expansion --> runtime analysis: https://informatika.stei.itb.ac.id/~rinaldi.munir/Matdis/2016-2017/Makalah2016/Makalah-Matdis-2016-051.pdf
                MatrixConstructor subMatrix = ExcludingSubMatrix(0, i);
                withoutIndices.add(i);
                double subMatrixDet = subMatrix.Determinant();
                withoutIndices.remove(withoutIndices.size() - 1);
                determinant = determinant + (Math.pow(-1, i % 2) * (this.values.get(0).get(i) * subMatrixDet));
            }
        }
        ArrayList<Integer> myKey = new ArrayList(withoutIndices);
        determinantTracker.put(myKey, determinant);
        return determinant;
    }

    //calculates rank of matrix, assumes matrix is square
    //is a way to make this faster by storing the rank of the submatrices in the dict
    public int Rank() {
        if (this.Determinant() != 0.0) {
            System.out.println("full rank");
            return this.row;
        }
        for (int i = 0; i < this.row; i++){
            for (HashMap.Entry<ArrayList<Integer>, Double> entry : determinantTracker.entrySet()) {
                ArrayList<Integer> key = entry.getKey();
                Double value = entry.getValue();
                // might need another for loop iterating through all the values of key.size()
                if (key.size() == i && value != 0) {
                    System.out.println("lin ind submatrix size: " + (this.row - i) + " by " + (this.row - i));
                    System.out.println("indices of lin ind submatrix: " + ColumnIndices(this.row, key));
                    System.out.println("rank: " + (this.row - i));
                    return this.row - i;
                }
            }
        }
        return 0;
    }

    public ArrayList<Integer> ColumnIndices(int size, ArrayList<Integer> excluded){
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++){
            indices.add(i);
        }
        for (int elem : excluded){
            indices.remove(elem);
        }
        return indices;
    }



}

