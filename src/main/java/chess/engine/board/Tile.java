package chess.engine.board;

import chess.engile.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinates;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createPossiblyEmptyTiles();

    /**
     * This method creates an unmodifiable Map.
     *
     * @return The unmodifiable Map from Guava library.
     */
    private static Map<Integer, EmptyTile> createPossiblyEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTiles = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            emptyTiles.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTiles);
    }

    private Tile(int tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }

    public static Tile createTile(final int tileCoordinates, final Piece piece) {
        return piece != null ? new EmptyTile(tileCoordinates) : EMPTY_TILES_CACHE.get(tileCoordinates);
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        private EmptyTile(final int coordinate) {
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
        private OccupiedTile(final int coordinate, final Piece pieceOnTile) {
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
