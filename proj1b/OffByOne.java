public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        char tempX = x;
        char tempY = y;
        if (--x == y || tempX == --tempY) {
            return true;
        }
        return false;
    }
}
