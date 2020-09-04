package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int LENGTH = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * construct original world
     *
     * @param teTiles
     */
    public static void constructWorld(TETile[][] teTiles) {
        for (int i = 0; i < teTiles.length; i++) {
            for (int j = 0; j < teTiles[0].length; j++) {
                teTiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void tesselationOFHexagons(TETile[][] teTiles, int sideLength, int originalPositionX, int originalPositionY) {
        int originalColumnX = originalPositionX;
        int originalColumnY = originalPositionY;
        for (int i = 0; i < 5; i++) {
            addHexagon(teTiles, sideLength, originalColumnX, originalColumnY, randomTile());
            originalColumnY = nextYPositionOfSameColumn(originalColumnY, sideLength);

        }

        int leftLineX = nextXPosition(originalPositionX, sideLength, true);
        int leftLeftLineX = nextXPosition(leftLineX, sideLength, true);
        int rightLineX = nextXPosition(originalPositionX, sideLength, false);
        int rightRightLineX = nextXPosition(rightLineX, sideLength, false);
        int aboveColumnY = nextYPosition(originalPositionY, sideLength, false);
        int aboveAboveColumnY = nextYPosition(aboveColumnY, sideLength, false);
        for (int i = 0; i < 4; i++) {
            addHexagon(teTiles, sideLength, leftLineX, aboveColumnY, randomTile());
            addHexagon(teTiles, sideLength, rightLineX, aboveColumnY, randomTile());
            aboveColumnY = nextYPositionOfSameColumn(aboveColumnY, sideLength);
        }

        for (int i = 0; i < 3; i++) {
            addHexagon(teTiles, sideLength, leftLeftLineX, aboveAboveColumnY, randomTile());
            addHexagon(teTiles, sideLength, rightRightLineX, aboveAboveColumnY, randomTile());
            aboveAboveColumnY = nextYPositionOfSameColumn(aboveAboveColumnY, sideLength);

        }

    }

    /**
     * add a hexagon of side length sideLength to
     * a given position in the world.
     *
     * @param teTiles
     * @param sideLength side length of hexagon
     * @param positionX  X position
     * @param positionY  Y position
     */
    public static void addHexagon(TETile[][] teTiles, int sideLength, int positionX, int positionY, TETile teTile) {
        int count = 0;
        for (int y = 0; y < sideLength; y++) {
            for (int x = 0; x < sideLength + 2 * count; x++) {
                teTiles[x + positionX - count][y + positionY] = teTile;
            }
            count++;
        }
        count--;
        for (int y = sideLength; y < sideLength * 2; y++) {
            for (int x = sideLength + 2 * count - 1; x >= 0; x--) {
                teTiles[x + positionX - count][y + positionY] = teTile;
            }
            count--;
        }
    }

    /**
     * calculate the Hexagon X position in the same line
     *
     * @param positionX  the original X position of Hexagon
     * @param sideLength side length of Hexagon
     * @return the next Hexagon X position in the same line
     */
    public static int nextXPositionOfSameLine(int positionX, int sideLength) {
        return positionX + (sideLength - 1) * 2 + sideLength + sideLength;
    }

    /**
     * calculate the Hexagon Y position in the same line
     *
     * @param positionY  the original Y position of Hexagon
     * @param sideLength side length of Hexagon
     * @return the next Hexagon Y position in the same line
     */

    public static int nextYPositionOfSameLine(int positionY, int sideLength) {
        return positionY;
    }

    /**
     * calculate the Hexagon X position in the same Column
     *
     * @param positionX  the original X position of Hexagon
     * @param sideLength side length of Hexagon
     * @return the Hexagon X position in the same Column
     */
    public static int nextXPositionOfSameColumn(int positionX, int sideLength) {
        return positionX;
    }

    /**
     * calculate the Hexagon Y position in the same Column
     *
     * @param positionY  the original Y position of Hexagon
     * @param sideLength side length of Hexagon
     * @return the Hexagon Y position in the same Column
     */
    public static int nextYPositionOfSameColumn(int positionY, int sideLength) {
        return positionY + sideLength * 2;
    }

    /**
     * calculate the Hexagon X position in the different Column and different Line
     *
     * @param positionX   the original X position of Hexagon
     * @param sideLength  side length of Hexagon
     * @param leftOrRight true represents the output is in the left of the input
     *                    false represents the output is in the right of the input
     * @return the Hexagon X position in the different Column and different Line
     */

    public static int nextXPosition(int positionX, int sideLength, boolean leftOrRight) {
        if (leftOrRight) {
            return positionX - sideLength * 2 + 1;
        } else {
            return positionX + sideLength * 2 - 1;
        }
    }

    /**
     * calculate the Hexagon Y position in the different Column and different Line
     *
     * @param positionY    the original Y position of Hexagon
     * @param sideLength   side length of Hexagon
     * @param belowOrAbove true represents the output is below input
     *                     false represents the output is above input
     * @return the Hexagon Y position in the different Column and different Line
     */

    public static int nextYPosition(int positionY, int sideLength, boolean belowOrAbove) {
        if (!belowOrAbove) {
            return positionY + sideLength;
        } else {
            return positionY - sideLength;
        }
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.FLOOR;
            case 3:
                return Tileset.GRASS;
            default:
                return Tileset.NOTHING;
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, LENGTH);
        TETile[][] random = new TETile[WIDTH][LENGTH];

        constructWorld(random);
        tesselationOFHexagons(random, 3, 30,0);
        ter.renderFrame(random);

    }
}
