package player;
public class GameBoardTester {

    private static final int EMPTY = -1;
    private static final int WHITE = 1;
    private static final int BLACK = 2;

    public static void main (String [] args) {
        Gameboard board1 = new Gameboard ();
        board1.initializeBoard();

        //Testing isValid.....

        //Testing corners
        Move move1 = new Move (0, 0);
        Move move2 = new Move (7, 0);
        Move move3 = new Move (0, 7);
        Move move4 = new Move (7, 7);
     
        System.out.println ("Testing isValid...");
        System.out.println ("Testing corner rule...");
        if (board1.isValid (move1, WHITE)) {
            System.out.println ("Top left Corner rule test failed.");
	}
        if (board1.isValid (move2, WHITE)) {
            System.out.println ("Top right corner rule test failed.");
	}
        if (board1.isValid (move3, WHITE)) {
            System.out.println ("Bottom left corner rule test failed.");
	}
        if (board1.isValid (move4, WHITE)) {
            System.out.println ("Bottom right corner rule test failed.");
	}
        else {
            System.out.println ("Corner rule test passed.");
	}

        //Testing rule 2
        move1 = new Move (1, 0);
        move2 = new Move (0, 3);
        move3 = new Move (7, 2);
        move4 = new Move (4, 7);
        if (board1.isValid (move1, WHITE)) {
            System.out.println ("Rule 2 test failed. 1");
	}
        if (!board1.isValid (move2, WHITE)) {
            System.out.println (board1.board[0][3]);
            System.out.println ("Rule 2 test failed. 2");
	}
        if (!board1.isValid (move3, WHITE)) {
            System.out.println ("Rule 2 test failed. 3");
	}
        if (board1.isValid (move4, WHITE)) {
            System.out.println ("Rule 2 test failed. 4");
	}
        if (!board1.isValid (move1, BLACK)) {
            System.out.println ("Rule 2 test failed. 5");
	}
        if (board1.isValid (move2, BLACK)) {
            System.out.println ("Rule 2 test failed. 6");
	}
        if (board1.isValid (move3, BLACK)) {
            System.out.println ("Rule 2 test failed. 7");
	}
        if (!board1.isValid (move4, BLACK)) {
            System.out.println ("Rule 2 test failed. 8");
	}
        board1.makeMove (WHITE, new Move (3, 3));
        move1 = new Move (3, 3, 3, 3);
        if (board1.isValid (move1, WHITE)) {
            System.out.println ("Replace rule failed.");
	}
        

        //Testing rule 3
        move1 = new Move (2, 3);
        move2 = new Move (2, 3);
        move3 = new Move (3, 4, 2, 3);
        board1.makeMove(WHITE, move1);
        if (board1.isValid(move2, WHITE)) {
            System.out.println ("Rule 3 failed");
	}
        if (board1.isValid(move2, BLACK)) {
            System.out.println ("Rule 3 failed");
	}
        board1.makeMove(WHITE, move3);
        if (!board1.isValid(move2, WHITE)) {
            System.out.println ("Rule 3 failed");
	}
        if (!board1.isValid(move2, BLACK)) {
            System.out.println ("Rule 3 failed");
	}
        if (!board1.isValid(move2, WHITE)) {
            System.out.println ("Rule 3 failed");
	}
        else {
            System.out.println ("Rule 3 passed.");
	    } 

        
        //Testing cluster rule
        Gameboard board2 = new Gameboard();
        board2.initializeBoard();
        move1 = new Move (2, 1);
        move2 = new Move (3, 0);
        board2.makeMove (WHITE, move1);
        board2.makeMove (WHITE, move2);

        //Illegal moves
        move3 = new Move (1, 0);
        move4 = new Move (2, 0);
        Move move5 = new Move (1, 1);
        Move move6 = new Move (1, 2);
        Move move7 = new Move (2, 2);
        Move move8 = new Move (3, 2);
        Move move9 = new Move (3, 1);
        Move move10 = new Move (4, 1);
        Move move11 = new Move (4, 0);

        if (board2.isValid(move3, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move4, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move5, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move6, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move7, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move8, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move9, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move10, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move11, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        
        Move move12 = new Move (2, 5);
        Move move13 = new Move (4, 5);
        board2.makeMove (BLACK, move12);
        board2.makeMove (BLACK, move13);

        //Illegal moves
        Move move14 = new Move (3, 4);
        Move move15 = new Move (3, 5);
        Move move16 = new Move (3, 6);

        //Legal moves for white
        Move move17 = new Move (3, 4);
        Move move18 = new Move (3, 5);
        Move move19 = new Move (3, 6);

        //Legal moves for black
        Move move20 = new Move (1, 6);
        Move move21 = new Move (1, 5);
        Move move22 = new Move (4, 4);        
        Move move23 = new Move (4, 6);
        Move move24 = new Move (5, 4);

        if (board2.isValid(move14, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move15, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (board2.isValid(move16, BLACK)) {
            System.out.println ("Cluster test failed");
	}

        if (!board2.isValid(move17, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move18, WHITE)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move19, WHITE)) {
            System.out.println ("Cluster test failed");
	}

        if (!board2.isValid(move20, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move21, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move22, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move23, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        if (!board2.isValid(move24, BLACK)) {
            System.out.println ("Cluster test failed");
	}
        else {
	    System.out.println ("Cluster test passed.");
	}

	//Testing findConnections....
        System.out.println ("isValid tests passed.");
        System.out.println ("Testing findConnections...");
        Gameboard board3 = new Gameboard();
        board3.initializeBoard();
        move1 = new Move (2, 2);
        board3.makeMove (WHITE, move1);

        move2 = new Move (2, 4);
        board3.makeMove(WHITE, move2);
        System.out.println ("Result should be 2: " + board3.findConnections(WHITE));
	board3.undoMove(WHITE, move2);

        move2 = new Move (0, 2);
        board3.makeMove(WHITE, move2);
        System.out.println ("Result should be 2: " + board3.findConnections(WHITE));
	board3.undoMove(WHITE, move2);

        move2 = new Move (4, 2);
        board3.makeMove(BLACK, move2);
        System.out.println ("Result should be 0: " + board3.findConnections(WHITE));
	board3.undoMove(BLACK, move2);

        move2 = new Move (2, 0);
        board3.makeMove(WHITE, move2);
	System.out.println ("Result should be 2: " + board3.findConnections(WHITE));
	board3.undoMove(WHITE, move2);

        move2 = new Move (4, 0);
        board3.makeMove(WHITE, move2);
	System.out.println ("Result should be 2: " + board3.findConnections(WHITE));
	board3.undoMove(WHITE, move2);  

        move2 = new Move (6, 6);
        board3.makeMove(WHITE, move2);
        System.out.println ("Result should be 2: " + board3.findConnections(WHITE));
        board3.makeMove(WHITE, new Move (6, 2));
        System.out.println ("Result should be 6: " + board3.findConnections(WHITE));

	//Testing evaluateBoard ...
        System.out.println ("Testing evaluateBoard...");
        Gameboard board4 = new Gameboard();
        board4.initializeBoard();
        move1 = new Move (1, 0);
        board4.makeMove (BLACK, move1);
        move2 = new Move (2, 0);
        board4.makeMove (BLACK, move2);
        move3 = new Move (4, 3);
        board4.makeMove (BLACK, move3);
        move4 = new Move (2, 4);
        board4.makeMove (BLACK, move4);
        move5 = new Move (2, 7);
        board4.makeMove (BLACK, move5);
        move6 = new Move (5, 6);
        board4.makeMove (BLACK, move6);

        move1 = new Move (1, 1);
        board4.makeMove (WHITE, move1);
        move2 = new Move (4, 1);
        board4.makeMove (WHITE, move2);
        move3 = new Move (0, 2);
        board4.makeMove (WHITE, move3);
        move4 = new Move (6, 2);
        board4.makeMove (WHITE, move4);
        move5 = new Move (1, 4);
        board4.makeMove (WHITE, move5);
        move6 = new Move (4, 4);
        board4.makeMove (WHITE, move6);

        System.out.println ("Odds for white: " + board4.evaluateBoard(WHITE));
        System.out.println ("Odds for black: " + board4.evaluateBoard(BLACK));
       
        printBoard(board4);
        board4.makeMove (WHITE, new Move (7, 3));
        System.out.println ("Making move 7 3");
        System.out.println ("Network status on new board: " + board4.network(WHITE, 0, 0, null, 0, 0));
        System.out.println ("Printing new board: ");
        printBoard(board4);

        //System.out.println ("White player should win with 10000: " + board4.evaluateBoard(WHITE));

        board4.undoMove (WHITE, new Move (7, 3));

	//Testing undoMove
        System.out.println ("Testing undoMove by undoing last move... printing new board.");
        printBoard(board4);

                
        //Testing returnBest ...
        System.out.println ("Testing minimax search with alpha beta pruning...");
        Best bestmove = board4.returnBest (WHITE, 1, -10000, 10000);
        System.out.println ("Best move should be (7, 1): " + bestmove.move.x1 + " " + bestmove.move.y1);
        board4.makeMove (WHITE, bestmove.move);
        /*Move testmove = new Move (7,6);
        System.out.println ("Trying test move 7 6 WHITE");
        board4.makeMove(WHITE, testmove);
        System.out.println ("Network status after making testmove: " + board4.network (WHITE, 0, 0, null, 0, 0));*/

        System.out.println ("New score after best move for white: " + board4.evaluateBoard(WHITE));
	System.out.println ("Network status after making best move " + bestmove.move.x1 + bestmove.move.y1 + " is "+ board4.network(WHITE, 0, 0, null, 0, 0));
     	printBoard(board4);

        System.out.println();
        System.out.println ("Setting up Test5 scenario of the autograder...");
        Gameboard board5 = new Gameboard();
        board5.initializeBoard();
        move1 = new Move (1, 1);
        board5.makeMove (BLACK, move1);
        move2 = new Move (2, 1);
        board5.makeMove (BLACK, move2);
        move3 = new Move (4, 1);
        board5.makeMove (BLACK, move3);
        move4 = new Move (5, 1);
        board5.makeMove (BLACK, move4);
        move5 = new Move (0, 2);
        board5.makeMove (WHITE, move5);
        move6 = new Move (1, 2);
        board5.makeMove (WHITE, move6);
        move7 = new Move (4, 2);
        board5.makeMove (WHITE, move7);
        move8 = new Move (1, 5);
        board5.makeMove (WHITE, move8);
        move9 = new Move (4, 5);
        board5.makeMove (WHITE, move9);
        move10 = new Move (1, 6);
        board5.makeMove (BLACK, move10);
        printBoard(board5);
        bestmove = board5.returnBest (WHITE, 4, -10000, 10000);
        System.out.println ("Best move should be add to 72, 75 is also acceptable.");
        System.out.println ("Move returnBest chooses is: " + bestmove.move.x1 + " " + bestmove.move.y1 + " with a score after of " + bestmove.score);
        System.out.println ("After move, the board looks like: ");
        board5.makeMove (WHITE, bestmove.move);
        printBoard (board5);
        System.out.println ("Network status after making best move on this board: " + board5.network(WHITE, 0, 0, null, 0, 0));
        System.out.println ("Evaluate board's score for this board: " + board5.evaluateBoard (WHITE));
        


    }

    public static void printBoard (Gameboard b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String character = "";
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 7 && j == 7) || (i == 0 && j == 7))
                    character = " ";
                else if (b.board[j][i] == -1)
                    character = "+";
                else if (b.board[j][i] == 1)
                    character = "W";
                else if (b.board[j][i] == 2)
                    character = "B";
                else
                    character = "O";
                System.out.print (character + "  ");
	    }
            System.out.println();
	}
    }

}
