
package Chess;

import BoardGame.BoardNotFinal;

public class ChessMatch {
    
    private BoardNotFinal board;
    
    public ChessMatch() {
        board = new BoardNotFinal(8, 8);
    }
    
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for ( int j = 0; j < board.getColumns(); j++) {
                // Not implmented yet
            }
        }
        return mat;
    }
}
