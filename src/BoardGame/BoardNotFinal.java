
package BoardGame;

public class BoardNotFinal {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;
    
    public BoardNotFinal(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public void setColumns(int columns) {
        this.columns  = columns;
    }
    
    public Piece piece(int row, int column) {
        return this.pieces[row][column];
    }
    
    public Piece piece(Position position) {
        return this.pieces[position.getRow()][position.getColumn()];
    }
}
