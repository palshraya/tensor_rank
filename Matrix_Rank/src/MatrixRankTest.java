import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
public class MatrixRankTest {
    @Test
    //checks whether Identity generating function works
    public void printIdentity(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        System.out.println(identity.values);
    }

    @Test
    //checks whether excluding submatrix method works
    public void IdExcludeSub(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        MatrixConstructor removed = identity.ExcludingSubMatrix(0, 0);
        System.out.println(removed.values);
    }

    @Test
    //checks whether excluding submatrix method works for iterated
    public void IdExcludeSubIter(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        for (int i = 0; i < 4; i++) {
            MatrixConstructor removed = identity.ExcludingSubMatrix(0, i);
            System.out.println(removed.values);
        }
    }

    @Test
    //check whether determinant on identity works
    public void IdentityDeterminant(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        System.out.println(identity.Determinant());
    }

    @Test
    //check whether determinant on more complicated matrix works
    public void ComplexDeterminant(){
        int[] myElems = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MatrixConstructor matrix = new MatrixConstructor(3, 3, myElems);
        System.out.println(matrix.Determinant());
        System.out.println(matrix.determinantTracker);
    }

    @Test
    //check rank method for complex matrix
    public void RankMoreComplex(){
        int[] myElems = new int[]{93, 186, 12, 270, 28, 61, 122, 35, 183, 19, 63, 126, 8, 189, 25, 57, 114, 94, 171, 72, 6, 12, 84, 18, 11};
        MatrixConstructor matrix = new MatrixConstructor(5, 5, myElems);
        System.out.println("the matrix is: " + matrix.values);
        matrix.Rank();
    }

    @Test
    //check rank method for more simple matrix
    public void RankMoreSimple(){
        int[] myElems = new int[]{1, 2, 3, 4, 2, 4, 6, 8, 3, 6, 2, 6, 4, 8, 6, 7};
        MatrixConstructor matrix = new MatrixConstructor(4, 4, myElems);
        System.out.println("the matrix is: " + matrix.values);
        matrix.Rank();
    }

    @Test
    // check product of two vectors
    public void checkProduct() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);

        ArrayList<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(5);
        b.add(6);

        MatrixConstructor m = new MatrixConstructor(3, 3, HeatMapData.product(a, b));
        m.Rank();

    }

    @Test
    //checks matrix generator
    public void checkGen() {
        MatrixConstructor generated = HeatMapData.matrixGenerator(5, 3);
        System.out.println(generated.values);
        generated.Rank();
    }

    @Test
    //generates the data
    public void checkData() {
        HeatMapData.dataMaker(10, 10);
        System.out.println(HeatMapData.DATA);
    }

    @Test
    //check toString method
    public void checkString() {
        int[] myElems = new int[]{1, 2, 5, 8, 9, 2, 3, 4, 5};
        MatrixConstructor matrix = new MatrixConstructor(3, 3, myElems);
        System.out.println(matrix);
    }

    @Test
    //checks matrix extractor method
    public void checkExtractor() {
        int[] myElems = new int[]{1, 2, 5, 2, 9, 3, 5, 3, 6};
        MatrixConstructor myMatrix = new MatrixConstructor(3, 3, myElems);
        int rank = RankExtractor.extractor(myMatrix);
        System.out.println(rank);
    }

    @Test
    //checks matrix extractor method
    public void checkExtractorby4() {
        int[] myElems = new int[]{1, 2, 3, 4, 2, 4, 6, 8, 3, 6, 2, 6, 4, 8, 6, 7};
        MatrixConstructor myMatrix = new MatrixConstructor(4, 4, myElems);
        int rank = RankExtractor.extractor(myMatrix);
        System.out.println(rank);
    }

    @Test
    //checks row reducer
    public void checkRREF() {
        MatrixConstructor myMatrix = HeatMapData.matrixGenerator(5, 5);
        System.out.println(myMatrix);
        double[][] rref = RowReducer.rref(myMatrix.formatConverterToArray());
        System.out.println(MatrixConstructor.formatConverterFromArray(rref));
    }


}
