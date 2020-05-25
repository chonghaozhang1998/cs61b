import org.junit.Test;


import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
//        The following test if for isPalindrome(String word);
//        assertFalse(palindrome.isPalindrome("cat"));
//        assertFalse(palindrome.isPalindrome("aA"));
//        assertTrue(palindrome.isPalindrome("noon"));
//        assertTrue(palindrome.isPalindrome("racecar"));
//        assertTrue(palindrome.isPalindrome("aaaaaaaaaa"));
//        The following test if for isPalindrome(String word, CharacterComparator cc);
        CharacterComparator cc = new OffByOne();
        assertEquals(true, palindrome.isPalindrome("ab", cc));
    }
}
