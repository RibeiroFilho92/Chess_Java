
package Chess;

import BoardGame.BoardNotFinal;
import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPiece extends Piece {
    
    private Color color;

    public ChessPiece(BoardNotFinal board, Color color) {
        super(board);
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
