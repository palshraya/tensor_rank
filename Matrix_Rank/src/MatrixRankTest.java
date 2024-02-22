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
        for (int i = 0; i < 3; i++) {
            MatrixConstructor removed = identity.ExcludingSubMatrix(0, i);
            System.out.println(removed.values);
        }
    }

    @Test
    //check whether determinant function on identity works
    public void IdentityDeterminant(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        System.out.println(identity.Determinant());
    }
}