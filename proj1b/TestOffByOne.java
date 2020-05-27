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
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(true, offByOne.equalChars('%', '&'));
        assertEquals(true, offByOne.equalChars('c', 'd'));
        assertEquals(true, offByOne.equalChars('A', 'A'));
        assertEquals(true, offByOne.equalChars('A', 'z'));
        assertEquals(false, offByOne.equalChars('A',  'a'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(false, offByOne.equalChars('d', 'b'));
    }
}
