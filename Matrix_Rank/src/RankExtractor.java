public class RankExtractor {
    // assumes matrix is symmetric
    public static int extractor(MatrixConstructor matrix) {
        MatrixConstructor myMatrix = new MatrixConstructor(matrix.row, matrix.col, matrix.rawVals);
        System.out.println(myMatrix);
        int rank = 0;
        for (int i = 0; i < myMatrix.row; i++) {
            int leadingNum = myMatrix.values.get(i).get(i);
            if (leadingNum != 0) {
                rank += 1;
                int[] oldVals = myMatrix.rawVals;
                //rank one matrix of outer product of row/col
                int[] rankOne = HeatMapData.product(myMatrix.values.get(i), myMatrix.values.get(i));
                //subtract
                for (int j = 0; j < oldVals.length; j++) {
                    oldVals[j] = oldVals[j] - (rankOne[j] / leadingNum);
                }
                myMatrix = new MatrixConstructor(matrix.row, matrix.col, oldVals);
                System.out.println(myMatrix);
            }
        }

        return rank;
    }

}
