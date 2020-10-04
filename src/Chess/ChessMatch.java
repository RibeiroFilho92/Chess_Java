
package Chess;

import BoardGame.BoardNotFinal;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.Pieces.King;
import Chess.Pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    
    private BoardNotFinal board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private List<Piece> piecesOnBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    
    public ChessMatch() {
        board = new BoardNotFinal(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        this.initialSetup();
        
    }
    
    public int getTurn() {
        return this.turn;
    }
    
    public Color getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public boolean getCheck(){
        return this.check;
    }
    
    public boolean getCheckMate() {
        return this.checkMate;
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
    
    public boolean[][] possibleMoves(ChessPosition findPosition) {
        Position position = findPosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    public ChessPiece performChessMove(ChessPosition findPosition, ChessPosition foundPosition) {
        Position find = findPosition.toPosition();
        Position found = foundPosition.toPosition();
        validateSourcePosition(find);
        validateTargetPosition(find, found);
        Piece capturedPiece = makeMove(find, found);
        if (testCheck(currentPlayer)) {
            undoMove(find, found, capturedPiece);
            throw new ChessException("Hey! You can not put yourself in check!");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;
        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }
        return (ChessPiece)capturedPiece;
    }
    
    private Piece makeMove(Position find, Position found) {
        ChessPiece p = (ChessPiece)board.removePiece(find);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(found);
        board.placePiece(p, found);
        if (capturedPieces != null) {
            piecesOnBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    
    private void undoMove(Position find, Position found, Piece capturedPiece) {
        ChessPiece p = (ChessPiece)board.removePiece(found);
        p.decreaseMoveCount();
        board.placePiece(p, find);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, found);
            capturedPieces.remove(capturedPiece);
            piecesOnBoard.add(capturedPiece);
        }
    }
    
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is not a piece on that position.");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("You can not choose that!");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There isn't possible moves for the chosen piece");
        }
    }
    
    private void validateTargetPosition(Position find, Position found) {
        if (!board.piece(find).possibleMove(found)) {
            throw new ChessException("This piece can't move there!");
        }
    }
    
    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There isn't " + color + " king on the board.");
    }
    
    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position find = ((ChessPiece)p).getChessPosition().toPosition();
                        Position found = new Position(i, j);
                        Piece capturedPiece = makeMove(find, found);
                        boolean testCheck = testCheck(color);
                        undoMove(find, found, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnBoard.add(piece);
    }
    
    private void initialSetup() {
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
    }
}
