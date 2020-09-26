
package Program;

import BoardGame.BoardNotFinal;
import BoardGame.Position;
import Chess.ChessMatch;


public class ChessWithJava {

    public static void main(String[] args) {
      
        ChessMatch chessMatch = new ChessMatch();
        UserInterface.printBoard(chessMatch.getPieces());
    }
    
}
