
package Chess;

import BoardGame.BoardNotFinal;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.Pieces.King;
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
    
    public ChessPiece performChessMove(ChessPosition findPosition, ChessPosition foundPosition) {
        Position find = findPosition.toPosition();
        Position found = foundPosition.toPosition();
        validateSourcePosition(find);
        Piece capturedPiece = makeMove(find, found);
        return (ChessPiece) capturedPiece;
    }
    
    private Piece makeMove(Position find, Position found) {
        Piece p = board.removePiece(find);
        Piece capturedPiece = board.removePiece(found);
        board.placePiece(p, found);
        return capturedPiece;
    }
    
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is not a piece on that position.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There isn't possible moves for the chosen piece");
        }
    }
    
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
