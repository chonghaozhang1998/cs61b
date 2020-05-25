import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created
    // your CharacterComparator interface and
    // OffByOne class.
    @Test
    public void testEqualChars() {
        assertEquals(false, offByOne.equalChars('a', 'b'));
        assertEquals(false, offByOne.equalChars('A', 'a'));
        assertEquals(true, offByOne.equalChars('a', 'a'));
    }
}
