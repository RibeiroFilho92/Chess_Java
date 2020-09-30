
package BoardGame;

public abstract class Piece {
    
    protected Position position;
    private BoardNotFinal board;
    
    public Piece(BoardNotFinal board) {
        this.board = board;
        this.position = null;
    }
    
    protected BoardNotFinal getBoard() {
        return this.board;
    }
    
    public abstract boolean[][] possibleMoves();
    
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
