
package Chess;

import BoardGame.BoardNotFinal;
import Chess.Pieces.Rook;

public class ChessMatch {
    
    private BoardNotFinal board;
    
    public ChessMatch() {
        board = new BoardNotFinal(8, 8);
        this.initialSetup();
    }
    
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for ( int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    
    private void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));;
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
    }
}
