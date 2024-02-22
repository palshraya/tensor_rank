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
    //check whether determinant function on identity works
    public void IdentityDeterminant(){
        MatrixConstructor identity = new MatrixConstructor(4, 4, MatrixConstructor.NbyNIdentity(4));
        System.out.println(identity.Determinant());
    }
}
