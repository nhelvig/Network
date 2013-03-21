

//Hello!!!!!!
public class Gameboard {


	public int[][] board;
	private static final int EMPTY = -1;
	private static final int WHITE = 1;
  	private static final int BLACK = 2;
  	public final int ADD = 1;
    public final int STEP = 2;
  	private boolean hasNetwork;

	public Gameboard() {
		board = new int[8][8];
		hasNetwork = false;
	}

	public void initializeBoard () {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; i < 8; i++) {
                board[i][j] = EMPTY;
	    	}
		}
    }

    public void makeMove (int color, Move m) {
        if (m.moveKind == ADD) {
            board[m.x1][m.y1] = color;
	}
        if (m.moveKind == STEP) {
            board[m.x2][m.y2] = EMPTY;
            board[m.x1][m.y1] = color;
		}    
    }

    public void undoMove (int color, Move m) {
        if (m.moveKind == ADD) {
            board[m.x1][m.y1] = EMPTY;
		}
        if (m.moveKind == STEP) {
            board[m.x2][m.y2] = board[m.x1][m.y1];
            board[m.x1][m.y1] = EMPTY;
		}
    }
    
    public int evaluateBoard () {
        return 0;
    }

    public Best returnBest (int side, int searchdepth, int alpha, int beta) {
        final int COMPUTER = side; 
        final int HUMAN = switchSide(side);
        Best myBest = new Best();
        Best reply;
        Move [] legalmoves = validMoves (side);
        int i = 0;

        if (evaluateBoard() > 1000 || evaluateBoard() < -1000 || searchdepth == 0) {
            myBest.score = evaluateBoard();
            return myBest;
	}
        if (side == COMPUTER) {
            myBest.score = alpha;
	} 
        else {
            myBest.score = beta;
	}

        while (legalmoves[i] != null) {
            makeMove (side, legalmoves[i]);
            reply = returnBest (switchSide(side), searchdepth - 1, alpha, beta);
            undoMove (side, legalmoves[i]);
            if ((side == COMPUTER) && (reply.score >= myBest.score)) {
                myBest.move = legalmoves[i];
                myBest.score = reply.score;
                alpha = reply.score;
	    } 
            else if ((side == HUMAN) && (reply.score <= myBest.score)) {
	        myBest.move = legalmoves[i];
                myBest.score = reply.score;
                beta = reply.score;
	    }
	    if (alpha >= beta) {
                return myBest;
	    }
            i++;
	}
        return myBest;
    }

    public int switchSide (int side) {
        if (side == WHITE)
            side = BLACK;
        if (side == BLACK)
            side = WHITE;
        return side;
    }

  // Implemented by Nick
  // validMoves generates a list of all possible moves by going through each space of
  // the internal game board and checking whether or not that space is a valid space 
  // to play a piece. If it is, it is added to the Move[]. If it is not a valid move,
  // nothing happens. 
  // validMoves takes no parameters
  // returns an array of valid moves
  protected Move[] validMoves(int color) {
    Move[] valids = new Move[77];
    int index = 0;
    for (int x = 0; x <= 8; x++) {
      for (int y = 0; y <= 8; y++) { 
        Move move = new Move(x,y);
        if (isValid (move, color)) {
          valids[index] = move;
          index++;
        }
      }
    }
    return valids;
  }

  //Implemented by Nick
  //These would be in MachinePlayer.java
  //cChip functions are helper functions that find the closest chip of the same color
  //in the up, down, left, right, and diagonal spaces. 
  //@params color is the color of the chips you are looking for
  //        x - the location of the chip on the gameboard (x-axis)
  //        y - the location of the chip on the gameboard (y-axis)
  //@returns the coordinates of the closest chip of that color. if the closest chip is
  //         not of the same color, returns null

  protected int[] cChipsUp (int color, int x, int y) {
    int[] position = new int[2];
    while (y > 0) {
      y--;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
    position[0] = EMPTY;
    return position;
  }

  protected int[] cChipsDown (int color, int x, int y) {
    int[] position = new int[2];
    while (y < 7) {
      y++;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
    position[0] = EMPTY;
    return position;
  }

  protected int[] cChipsRight (int color, int x, int y) {
    int[] position = new int[2];
    while (x < 7) {
      x++;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
    position[0] = EMPTY;
    return position;
  }

  protected int[] cChipsLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (x > 0) {
      x--;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
    position[0] = EMPTY;
    return position;
  }

  protected int[] cChipsDiagUpLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (y > 0 && x > 0) {
      y--;
      x--;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
  position[0] = EMPTY;
  return position;
  }

  protected int[] cChipsDiagUpRight (int color, int x, int y) {
    int[] position = new int[2];
    while (y > 0 && x < 7) {
      y--;
      x++;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
  position[0] = EMPTY;
  return position;
  }

  protected int[] cChipsDiagDownLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (y < 7 && x > 0) {
      y++;
      x--;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
  position[0] = EMPTY;
  return position;
  }

  protected int[] cChipsDiagDownRight (int color, int x, int y) {
    int[] position = new int[2];
    while (y < 7 && x < 7) {
      y++;
      x++;
      if (board[x][y] == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
    }
  position[0] = EMPTY;
  return position;
  }

  //connectedChips finds all chips of the same color that are connected to the chip using the 
  //cChip functions and returns them as an array of chip coordinates.
  //@params  color - the color of the chip (black or white)
  //         x - the location of the chip on the gameboard (x-axis)
  //         y - the location of the chip on the gameboard (y-axis)
  //@returns an array of all the connected chip positions

  protected int[][] connectedChips(int color, int x, int y) {
    int[][] connected = new int[8][];
    if (cChipsUp(color, x, y) != null) {
      connected[0] = cChipsUp(color, x, y);
    }
    if (cChipsDiagUpRight(color, x, y) != null) {
      connected[1] = cChipsDiagUpRight(color, x, y);
    }
    if (cChipsRight(color, x, y) != null) {
      connected[2] = cChipsRight(color, x, y);
    }
    if (cChipsDiagDownRight(color, x, y) != null) {
      connected[3] = cChipsDiagDownRight(color, x, y);
    }
    if (cChipsDown(color, x, y) != null) {
      connected[4] = cChipsDown(color, x, y);
    }
    if (cChipsDiagDownLeft(color, x, y) != null) {
      connected[5] = cChipsDiagDownLeft(color, x, y);
    }
    if (cChipsLeft(color, x, y) != null) {
      connected[6] = cChipsLeft(color, x, y);
    }
    if (cChipsDiagUpLeft(color, x, y) != null) {
      connected[7] = cChipsDiagUpLeft(color, x, y);
    }
    return connected;
  }

  //inGoal() checks to see if a chip is in the goal area for a certain player. This will be used
  //to check the start and end points of the network. Since the start and end points both need to
  //be in a goal area, inGoal() will make sure the connection is valid for the start and end points
  //@params  color - the color of the player looking to see if his chip is in a goal
  //         x - the location of the chip on the gameboard (x-axis)
  //         y - the location of the chip on the gameboard (y-axis)
  //@returns true if the chip is in the goal
  //         false if the chip is not in the player's goal area
  public boolean inGoals(int color) {
  	boolean goal1 = false;
  	boolean goal2 = false;
    if (color == WHITE) {
		for (int y = 1; y < 7; y++) {
			if (board[0][y] == WHITE) {
				goal1 = true;
			}
			if (board[7][y] == WHITE) {
				goal2 = true;
			}
		}
	} else if (color == BLACK) {
		for (int x = 1; x < 7; x++) {
			if (board[x][0] == BLACK) {
				goal1 = true;
			}
			if (board[x][7] == BLACK) {
				goal2 = true;
			}
		}
    }
    return goal1 && goal2;
  }

  //Checks to see if the current position is in the goal on the winning side for the given color.
  //Since Network() always starts on the top or left side, this checks the goal for the bottom
  //or right side.
  private boolean inWinningGoal(int color, int x, int y) {
  	if (color == WHITE) {
  		return x == 7;
  	} else if (color == BLACK) {
  		return y == 7;
  	}
  	return false;
  }

  //Checks to see if a given chip is in the starting goal of a board. The starting goal is the top of
  //the board for BLACK and the left side for WHITE. This is called during Network().
  private boolean inStartingGoal(int color, int x, int y) {
  	if (color == WHITE) {
  		return x == 0;
  	} else if (color == BLACK) {
  		return y == 0;
  	}
  	return false;
  }
  //Implemented by Nick
  //Network() evaluates a board and check's each chips list of connections to see if 
  //there is a Network of 6 or more chips. Uses an array to make sure that the same chip is not 
  //called more than once. Also keeps track of the direction of the chip so that two chips in a
  //line are not allowed to be in the same network. Makes sure the beginning and ending chips are
  //in the goal areas using inGoal().
  //@params color - the color of the player searching for a network
  //		x - x-coordinate of the current chip (used in recursive calls, default call will be 0)
  //		y - y-coordinate of the current chip (used in recursive calls, default call will be 0)
  //		checked - the list of previously used chips (used in recursive calls, default call will be null)
  //		total - length of the current network, default is 0
  //		directionFrom - during the function call, direction from is the index position of the list of
  //						connected chips. Because each element in the array of connected chips corresponds
  //						to a certain direction, this allows future calls to not need to search in that direction.
  //@returns true if there is a Network for the given player
  //         false if there are no valid Networks
  

public boolean network(int color, int x, int y, int[][] checked, int total, int directionFrom) {
	if (x == 0 && y == 0 && total == 0 && !inGoals(color)) {
		return false;
	}
	if (total >= 5 && inWinningGoal(color, x, y)) {
		System.out.println("beginning input " + x +" " + y);
		for (int i[] : checked) {
				System.out.println("x,y: " + i[0] + " " + i[1]);
			}
		hasNetwork = true;
	}
	if (color == BLACK) {
		if (total == 0) {
		//Iterate through all of the starting goal spaces to find a chip in the goal
		//from which to test for a network.	
			for (int column = 1; column < 7; column++) {
				if (board[column][0] == color) {
					int[][] newChecked = new int[10][2];
					newChecked[0][0] = column;
					newChecked[0][1] = 0;
					total++;
					network(color, column, 0, newChecked, total, EMPTY);				
				}
			}
		} else {
			//Make a list of all chip positions connected to this position.
			int[][] connected = connectedChips(color, x, y);
			//To avoid making a connection of 3+ chips in a row, do not search in the same direction.
			if (directionFrom != EMPTY) {
				connected[directionFrom][0] = EMPTY;
			}
			for (int index = 0; index < connected.length; index++) {
				//If any chips in the connected list are the same as any in the list that has already been checked,
				//or if a connected chip is in the starting goal, delete that chip from the list of connected chips.
				for (int checkIndex = 0; checkIndex < checked.length; checkIndex++) {
					if (connected[index][0] != EMPTY){
						int connectedX = connected[index][0];
						int connectedY = connected[index][1];
						int checkedX = checked[checkIndex][0];
						int checkedY = checked[checkIndex][1];
						if (connectedX == checkedX && connectedY == checkedY || inStartingGoal(color, connectedX, connectedY)) {
							connected[index][0] = EMPTY;
						}
					}
				}
				//With a now valid list of connected chips, call Network() on each of the connected chips, with directionFrom
				//being the indexed position of connected.
				if (connected[index][0] != EMPTY) {
					//Create a pointer array to the checked array called current, allowing modification of an array that will be 
					//called in Network().
					int[][] current = checked;
					for (int i[] : connected) {
						System.out.println("CONNECTED for " + x + " " + y + " x,y: " + i[0] + " " + i[1]);
					}
					current[total][0] = connected[index][0];
					current[total][1] = connected[index][1];
					total++;
					network(color, connected[index][0], connected[index][1], current, total, index);
				}
			}
		}
	} else if (color == WHITE) {
		if (total == 0) {
		//Iterate through all of the starting goal spaces to find a chip in the goal
		//from which to test for a network.	
			for (int row = 1; row < 7; row++) {
				if (board[0][row] == color) {
					int[][] newChecked = new int[10][2];
					newChecked[0][0] = 0;
					newChecked[0][1] = row;
					total++;
					network(color, 0, row, newChecked, total, EMPTY);				
				}
			}
		} else {
			//Make a list of all chip positions connected to this position.
			int[][] connected = connectedChips(color, x, y);
			//To avoid making a connection of 3+ chips in a row, do not search in the same direction.
			if (directionFrom != EMPTY) {
				connected[directionFrom][0] = EMPTY;
			}
			for (int index = 0; index < connected.length; index++) {
				//If any chips in the connected list are the same as any in the list that has already been checked,
				//or if a connected chip is in the starting goal, delete that chip from the list of connected chips.
				for (int checkIndex = 0; checkIndex < checked.length; checkIndex++) {
					if (connected[index][0] != EMPTY ){
						int connectedX = connected[index][0];
						int connectedY = connected[index][1];
						int checkedX = checked[checkIndex][0];
						int checkedY = checked[checkIndex][1];
						if (connectedX == checkedX && connectedY == checkedY || inStartingGoal(color, connectedX, connectedY)) {
							connected[index][0] = EMPTY;
						}
					}
				}
				//With a now valid list of connected chips, call Network() on each of the connected chips, with directionFrom
				//being the indexed position of connected.
				if (connected[index][0] != EMPTY) {
					//Create a pointer array to the checked array called current, allowing modification of an array that will be 
					//called in Network().
					int[][] current = checked;
					current[total][0] = connected[index][0];
					current[total][1] = connected[index][1];
					total++;
					network(color, connected[index][0], connected[index][1], current, total, index);
				}
			}
		}
	}
	return hasNetwork;
	}
  
    public boolean isValid (Move m, int color) {
        Gameboard test = new Gameboard ();
        test.board = this.board;


        //No chip may be placed in any of the four corners
        if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 7))
            return false;

        //No chip may be placed in a goal of the opposite color
        if ((color == WHITE) && (m.y1 == 0 || m.y1 == 7)) 
            return false;
        if ((color == BLACK) && (m.x1 == 0 || m.x1 == 7)) 
            return false;

	//No chip may be placed in a square that is already occupied
      	if (test.board [m.x1][m.y1] != EMPTY)
            return false;

        if (m.moveKind == STEP)
       	    test.board [m.x2][m.y2] = EMPTY;

	//A player may not have more than two chips in a connected group, whether connected orthogonally or diagonally
        int [] adjacentPiece = checkAround (test, m.x1, m.y1);
        if (adjacentPiece [0] == -1 && adjacentPiece[1] == -1)
            return true;
        if (adjacentPiece [0] == -2 && adjacentPiece[1] == -2)
            return false;
        
        adjacentPiece = checkAround (test, adjacentPiece[0], adjacentPiece[1]);
        if (adjacentPiece [0] == -1 && adjacentPiece[1] == -1)
            return true;

        return false;
    }

    private int [] checkAround (Gameboard test, int x, int y) {
	int [] position = new int [2];
        position [0] = -1;
        position [1] = -1;
        int count = 0;

	// Check all around position for board
	for (int i = -1; i <= 1; i++) {
	    //Left border case
            if (x == 0)
                i = 0;
            //Right border case
            if (x == 7 && i == 1) 
                continue;

            for (int j = -1; j <= 1; j++) {
                //Don't return starting position
                if (i == 0 && j == 0) 
                    continue;
                //Top border case
                if (y == 0)
                    j = 0;
                //Bottom border case
                if (y == 7 && j == 1)
                    continue;

                
                if (test.board[x+i][y+j] != EMPTY) {
                    position[0] = x + i;
                    position[1] = y + j;
                    count ++;
                  
		}
	    }
	}

        if (count >= 2) {
            position [0] = -2;
            position [1] = -2;
	}

        return position;
    }
}
