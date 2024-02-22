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
        double[] myElems = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 9.0, 7.0, 8.0, 13.0};
        MatrixConstructor matrix = new MatrixConstructor(3, 3, myElems);
        System.out.println(matrix.Determinant());
    }
}
