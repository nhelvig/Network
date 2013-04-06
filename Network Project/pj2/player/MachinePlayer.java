/* MachinePlayer.java */

package player;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */


public class MachinePlayer extends Player {
    final int BLACK = 2;
    final int WHITE = 1;
    final int EMPTY = -1;
    public final static int QUIT = 0;
    public final static int ADD = 1;
    public final static int STEP = 2;
    int side;
    int depth;
    Gameboard myboard;

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
      if (color ==  0) {
          side = BLACK;
      }
      if (color == 1) {
          side = WHITE;
      }
      depth = 4;
      myboard = new Gameboard ();
      myboard.initializeBoard(); 
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
      if (color ==  0) {
          side = BLACK;
      }
      if (color == 1) {
          side = WHITE;
      }
      depth = searchDepth;
      myboard = new Gameboard();
      myboard.initializeBoard();

  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {
      Best myBest = myboard.returnBest(side, depth, -10000, 10000);
      Move bestmove = myBest.move;
      if (bestmove.moveKind != QUIT) {
          myboard.makeMove(side, bestmove);
          System.out.println ("Best: " + myBest.score);
          return bestmove;
      } 
      else {
          bestmove = myboard.validMoves(side)[0];
          myboard.makeMove (side, bestmove);
          return bestmove;
      }
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
      if (myboard.isValid(m, switchSide(side))) {
          myboard.makeMove(switchSide(side), m);
          return true;
      }
      else {
          return false;
      }
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
      if (myboard.isValid(m, side)) {
          myboard.makeMove(side, m);
          return true;
      }
      else {
          return false;
      }
  }

  public int switchSide (int side) {
        if (side == WHITE)
            return BLACK;
        if (side == BLACK)
            return WHITE; 
        return EMPTY;
    }

}
