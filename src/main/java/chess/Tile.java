package chess;

public abstract class Tile {
    int tileCoordinates;
    Tile(int tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        EmptyTile(int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }
}
