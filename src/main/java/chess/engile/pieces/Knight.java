package chess.engile.pieces;

import chess.Alliance;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Move.*;
import chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};//offsets w.r.t current position
    Knight (final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        int candidateDestinationCoordinate;

        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateMove : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePosition + candidateMove;
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {//is it valid coordinate
                if(isFirstColumnExclusion(this.piecePosition, candidateMove) ||
                    isSecondColumnExclusion(this.piecePosition, candidateMove) ||
                    isSeventhColumnExclusion(this.piecePosition, candidateMove) ||
                    isEighthColumnExclusion(this.piecePosition, candidateMove)) {//is it a valid move for the Knight
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()) {//MajorMove
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * This method determines whether the current candidateOffset leads to a valid move provided that the Knight is in the
     * first column
     *
     * @param currentPosition
     * @param candidateOffset
     * @return true if the candidateOffset leads to an invalid move
     * @return false if the candidateOffset leads to a valid move
     */
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10
                || candidateOffset == 6 || candidateOffset == 15);
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 17 || candidateOffset == 10
                || candidateOffset == -6 || candidateOffset == -15);
    }

}
