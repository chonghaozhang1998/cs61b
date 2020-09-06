package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(60, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(CHARACTERS[this.rand.nextInt(26)]);
        }
        return stringBuilder.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear();
        StdDraw.text(this.width / 2, this.height / 2, s);
        //TODO: If game is not over, display relevant game information at the top of the screen
        if (!gameOver) {
            StdDraw.textLeft(1, this.height - 2, "Round: " + this.round);
            StdDraw.text(this.width / 2, this.height - 2, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(this.width - 1, this.height - 2, ENCOURAGEMENT[this.round % ENCOURAGEMENT.length]);
        }
        StdDraw.show();
        StdDraw.pause(500);
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char[] chars = letters.toCharArray();
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(String.valueOf(chars[i]));
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            while (!StdDraw.hasNextKeyTyped()) ;
            //when users do not type any keys, program just stops here and waits for input of users.
            stringBuilder.append(StdDraw.nextKeyTyped());
            drawFrame(stringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        this.round = 1;
        this.gameOver = false;
        this.playerTurn = false;

        //TODO: Establish Game loop
        while (!gameOver) {
            drawFrame("Round: " + this.round);
            String randomString = this.generateRandomString(round);
            this.playerTurn = false;
            flashSequence(randomString);
            this.playerTurn = true;
            String userInputString = solicitNCharsInput(round);
            if (userInputString.equals(randomString)) {
                round++;
            } else {
                gameOver = true;
                drawFrame("Game Over! You made it to round:" + this.round);
            }
        }
    }

}
