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
        double[] myElems = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        MatrixConstructor matrix = new MatrixConstructor(3, 3, myElems);
        System.out.println(matrix.Determinant());
        System.out.println(matrix.determinantTracker);
    }

    @Test
    //check rank method for simple matrix
    public void RankSimple(){
        double[] myElems = new double[]{93.0, 186.0, 12.0, 279.0, 28.0, 61.0, 122.0, 35.0, 183.0, 19.0, 63.0, 126.0, 8.0, 189.0, 25.0, 57.0, 114.0, 94.0, 171.0, 72.0, 6.0, 12.0, 84.0, 18.0, 11.0};
        MatrixConstructor matrix = new MatrixConstructor(5, 5, myElems);
        System.out.println("the matrix is: " + matrix.values);
        matrix.Rank();
    }

    @Test
    //check rank method for more complex matrix
    public void RankMoreComplex(){
        double[] myElems = new double[]{};
        MatrixConstructor matrix = new MatrixConstructor(3, 3, myElems);
        System.out.println("the matrix is: " + matrix.values);
        matrix.Rank();
    }

}
