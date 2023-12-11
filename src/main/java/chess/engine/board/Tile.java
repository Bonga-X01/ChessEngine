package chess.engine.board;

import chess.engile.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinates;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createPossiblyEmptyTiles();

    private static Map<Integer, EmptyTile> createPossiblyEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTiles = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            emptyTiles.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTiles);
    }

    Tile(int tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate) {
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
    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;
        OccupiedTile(int coordinate, Piece pieceOnTile) {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
