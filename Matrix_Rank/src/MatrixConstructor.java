import java.util.ArrayList;

public class MatrixConstructor {
    public int row;
    public int col;
    public ArrayList<ArrayList<Double>> values;

    //assumes correct dimensions are passed in
    public MatrixConstructor(int r, int c, double[] v) {
        this.row = r;
        this.col = c;
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

    public static double[] NbyNIdentity(int N) {
        double[] elems = new double[N * N];
        for (int i = 0; i < N; i++) {
            elems[i * N + i] = 1.0;
        }
        return elems;
    }

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

    public double Determinant() {
        double determinant = 0.0;
        if (this.values.size() == 1) {
            determinant = determinant + this.values.get(0).get(0);
        }
        else{
            for (int i = 0; i < this.values.size(); i++) {
                // cofactor expansion
                determinant = determinant + (this.values.get(0).get(i) * this.ExcludingSubMatrix(0, i).Determinant());
            }
        }
        return determinant;
    }
}
