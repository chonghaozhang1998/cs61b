public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = word.length() - 1; i >= 0; i--) {
            res.addFirst(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        int left = 0;
        int right = 0;
        if (word.length() % 2 == 0) {
            left = (word.length() - 1) / 2;
            right = left + 1;
        } else {
            left = word.length() / 2 - 1;
            right = word.length() / 2 + 1;
        }
        while (left >= 0 && right < word.length()) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        int left = 0;
        int right = 0;
        if (word.length() % 2 == 0) {
            left = (word.length() - 1) / 2;
            right = left + 1;
        } else {
            left = word.length() / 2 - 1;
            right = word.length() / 2 + 1;
        }
        while (left >= 0 && right < word.length()) {
            if (!cc.equalChars(word.charAt(left), word.charAt(right))) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }
}
