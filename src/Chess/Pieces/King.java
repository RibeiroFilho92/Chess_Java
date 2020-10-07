
package Chess.Pieces;

import BoardGame.BoardNotFinal;
import BoardGame.Position;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {
    
    private ChessMatch chessMatch;
    
    public King(BoardNotFinal board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }
    
    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    
    private boolean testRookCasting (Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
        Position positionOne = new Position(position.getRow(), position.getColumn() + 3);
        if (testRookCasting(positionOne)) {
            Position pOne = new Position(position.getRow(), position.getColumn() + 1);
            Position pTwo = new Position(position.getRow(), position.getColumn() + 2);
            if (getBoard().piece(pOne) == null && getBoard().piece(pTwo) == null) {
                mat[position.getRow()][position.getColumn() + 2] = true;
            }
        }
    }
        
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
        Position positionTwo = new Position(position.getRow(), position.getColumn() - 4);
        if (testRookCasting(positionTwo)) {
            Position pOne = new Position(position.getRow(), position.getColumn() - 1);
            Position pTwo = new Position(position.getRow(), position.getColumn() - 2);
            Position pThree = new Position(position.getRow(), position.getColumn() - 3);
            if (getBoard().piece(pOne) == null && getBoard().piece(pTwo) == null && getBoard().piece(pThree) == null) {
                mat[position.getRow()][position.getColumn() - 2] = true;
            }
        }
    }
        return mat;
    }
    
    @Override
    public String toString() {
        return "K";
    }

}
