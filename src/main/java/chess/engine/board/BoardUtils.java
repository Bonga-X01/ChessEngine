package chess.engine.board;

public class BoardUtils {

    /**
     * THIS ARRAY OF SIZE 64 HAS ALL VALUES FALSE, EXCEPT VALUES CORRESPONDING TO THE 1ST COLUMN IN THE CHESS BOARD
     */
    public static final boolean[] FIRST_COLUMN = null;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }
    public static boolean isValidTileCoordinate(int coordinate) {
        return coordinate >=0 && coordinate < 64;
    }
}
