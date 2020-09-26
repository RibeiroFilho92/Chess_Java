
package Chess.Pieces;

import BoardGame.BoardNotFinal;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {
    
    public King(BoardNotFinal board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString() {
        return "K";
    }
}
