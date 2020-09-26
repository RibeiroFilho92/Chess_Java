
package Chess.Pieces;

import BoardGame.BoardNotFinal;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {
    
    public Rook(BoardNotFinal board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString() {
        return "R";
    }
}
