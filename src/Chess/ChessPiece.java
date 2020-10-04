
package Chess;

import BoardGame.BoardNotFinal;
import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPiece extends Piece {
    
    private Color color;
    private int moveCount;

    public ChessPiece(BoardNotFinal board, Color color) {
        super(board);
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getMoveCount() {
        return this.moveCount;
    }
    
    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }
    
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
    
    public void increaseMoveCount() {
        this.moveCount++;
    }
    
    public void decreaseMoveCount() {
        this.moveCount--;
    }
}
