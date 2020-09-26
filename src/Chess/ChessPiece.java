
package Chess;

import BoardGame.BoardNotFinal;
import BoardGame.Piece;

public class ChessPiece extends Piece {
    
    private Color color;

    public ChessPiece(BoardNotFinal board, Color color) {
        super(board);
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
}
