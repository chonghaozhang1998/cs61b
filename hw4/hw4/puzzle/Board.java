package hw4.puzzle;

import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements WorldState {
    private int[][] board;

    // Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        int N = tiles.length;
        this.board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.board[i][j] = tiles[i][j];
            }
        }
    }

    // Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i < 0 || this.size() <= i || j < 0 || this.size() <= j) {
            IndexOutOfBoundsException exception = new IndexOutOfBoundsException();
            throw exception;
        }
        return this.board[i][j];
    }

    // Returns the board size N
    public int size() {
        return this.board.length;
    }

    // Returns the neighbors of the current board
    public Iterable<WorldState> neighbors() {
        List<WorldState> res = new ArrayList<>();
        int blankPosRow = 0;
        int blankPosCol = 0;
        boolean tag = false; // represent whether the blank pos is found or not
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.tileAt(i, j) == 0) {
                    blankPosRow = i;
                    blankPosCol = j;
                    tag = true;
                    break;
                }
            }
            if (tag) {
                break;
            }
        }
        if (isDirectionLegal(blankPosRow, blankPosCol, "right")) {
            res.add(new Board(movedBoard(this.board, blankPosRow, blankPosCol, "right")));
        }
        if (isDirectionLegal(blankPosRow, blankPosCol, "left")) {
            res.add(new Board(movedBoard(this.board, blankPosRow, blankPosCol, "left")));
        }
        if (isDirectionLegal(blankPosRow, blankPosCol, "above")) {
            res.add(new Board(movedBoard(this.board, blankPosRow, blankPosCol, "above")));
        }
        if (isDirectionLegal(blankPosRow, blankPosCol, "below")) {
            res.add(new Board(movedBoard(this.board, blankPosRow, blankPosCol, "below")));
        }
        return res;
    }

    // return a copy of board
    private int[][] copy() {
        int[][] copyBoard = new int[this.size()][this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                copyBoard[i][j] = this.tileAt(i, j);
            }
        }
        return copyBoard;
    }

    /**
     * decide whether the blank tile has right tile
     *
     * @param blankPosRow the row position of the blank tile
     * @param blankPosCol the col position of the blank tile
     * @return whether the blank tile has right tile
     */
    private boolean isDirectionLegal(int blankPosRow, int blankPosCol, String direction) {
        boolean res = true;
        switch (direction) {
            case "right":
                if (blankPosCol == this.size() - 1) {
                    res = false;
                }
                break;
            case "left":
                if (blankPosCol == 0) {
                    res = false;
                }
                break;
            case "above":
                if (blankPosRow == 0) {
                    res = false;
                }
                break;
            case "below":
                if (blankPosRow == this.size() - 1) {
                    res = false;
                }
                break;
        }
        return res;
    }

    /**
     * move the tile which is at the right of the blank tile to the blank tile Postion
     *
     * @param board       the original board
     * @param blankPosRow the row position of the blank tile
     * @param blankPosCol the col position of the blank tile
     * @return the moved board
     */
    private int[][] movedBoard(int[][] board, int blankPosRow, int blankPosCol, String direction) {
        int[][] movedBoard = copy();
        switch (direction) {
            case "right":
                movedBoard[blankPosRow][blankPosCol] = movedBoard[blankPosRow][blankPosCol + 1];
                movedBoard[blankPosRow][blankPosCol + 1] = 0;
                break;
            case "left":
                movedBoard[blankPosRow][blankPosCol] = movedBoard[blankPosRow][blankPosCol - 1];
                movedBoard[blankPosRow][blankPosCol - 1] = 0;
                break;
            case "above":
                movedBoard[blankPosRow][blankPosCol] = movedBoard[blankPosRow - 1][blankPosCol];
                movedBoard[blankPosRow - 1][blankPosCol] = 0;
                break;
            case "below":
                movedBoard[blankPosRow][blankPosCol] = movedBoard[blankPosRow + 1][blankPosCol];
                movedBoard[blankPosRow + 1][blankPosCol] = 0;
                break;
        }
        return movedBoard;
    }


    // Hamming estimate described below
    public int hamming() {
        int count = 0;
        int res = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                count++;
                if (tileAt(i, j) == 0) {
                    continue;
                } else if (tileAt(i, j) != count) {
                    res++;
                }
            }
        }
        return res;
    }

    // Manhattan estimate described below
    public int manhattan() {
        int res = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                int originalPosRow = this.changeIntegerToPosRow(tileAt(i,j));
                int originalPosCol = this.changeIntegerToPosCol(tileAt(i,j));
                res += Math.abs(originalPosRow - i) + Math.abs(originalPosCol - j);
            }
        }
        return res;
    }

    private int changeIntegerToPosRow (int i) {
        return (i - 1) / this.size();
    }

    private int changeIntegerToPosCol (int i) {
        return (i - 1) % this.size();
    }

    // Estimated distance to goal. This method should
    // simply return the results of manhattan() when submitted to
    // Gradescope.
    public int estimatedDistanceToGoal() {
        return this.manhattan();
    }

    // Returns true if this board's tile values are the same
    // position as y's
    public boolean equals(Object y) {
        Board board = (Board) y;
        boolean res = true;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (!Integer.valueOf(board.tileAt(i, j)).equals(this.tileAt(i, j))) {
                    res = false;
                    break;
                }
            }
            if (!res) {
                break;
            }
        }
        return res;
    }


    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
