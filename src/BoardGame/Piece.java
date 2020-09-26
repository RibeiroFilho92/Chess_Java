
package BoardGame;

public class Piece {
    
    protected Position position;
    private BoardNotFinal board;
    
    public Piece(BoardNotFinal board) {
        this.board = board;
        this.position = null;
    }
    
    private BoardNotFinal getBoard() {
        return this.board;
    }
    
    
}
