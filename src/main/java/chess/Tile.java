package chess;

public abstract class Tile {
    int tileCoordinates;
    Tile(int tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
}
