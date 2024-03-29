package chess.engine.board;

public class BoardUtils {

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    /**
     * THIS ARRAY OF SIZE 64 HAS ALL VALUES FALSE, EXCEPT VALUES CORRESPONDING TO THE 1ST COLUMN IN THE CHESS BOARD
     */
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while (columnNumber < NUM_TILES);
        return  column;
    }
    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >=0 && coordinate < NUM_TILES;
    }
}
