public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        char tempX = x;
        char tempY = y;
        x -= this.N;
        tempY -= this.N;
        if (x == y || tempX == tempY) {
            return true;
        }
        return false;
    }
}
