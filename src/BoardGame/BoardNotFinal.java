
package BoardGame;

public class BoardNotFinal {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;
    
    public BoardNotFinal(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new BoardException("Error creating board: it must has at least one row and one column.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public Piece piece(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Invalid position!");
        }
        return this.pieces[row][column];
    }
    
    public Piece piece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Invalid position!");
        }
        return this.pieces[position.getRow()][position.getColumn()];
    }
    
    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("Position already been used!");
        }
        this.pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
    
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }
    
    public boolean thereIsAPiece(Position position) {
         if(!positionExists(position)) {
            throw new BoardException("Invalid position!");
        }
        return piece(position) != null;
    }
}
