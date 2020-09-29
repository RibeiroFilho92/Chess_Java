
package Program;

import BoardGame.BoardNotFinal;
import BoardGame.Position;
import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ChessWithJava {

    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        
        while (true) {
            try {
               UserInterface.clearScreen();
                UserInterface.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition find = UserInterface.readChessPosition(sc);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition found = UserInterface.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(find, found); 
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            
        }

    }
    
}
