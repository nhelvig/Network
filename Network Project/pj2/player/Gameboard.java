//Hello!!!!!!
public class Gameboard {


	private int[][] board;

	public Gameboard() {
		board = new int[8][8];
	}

	public int getItem(int x, int y) {
		return board[x][y];
	}

  // Implemented by Nick
  // validMoves generates a list of all possible moves by going through each space of
  // the internal game board and checking whether or not that space is a valid space 
  // to play a piece. If it is, it is added to the Move[]. If it is not a valid move,
  // nothing happens. 
  // validMoves takes no parameters
  // returns an array of valid moves
  protected Move[] validMoves(int color) {
    Move[] valids;
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
    while (y >= 0) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y--;
    }
    return null;
  }

  protected int[] cChipsDown (int color, int x, int y) {
    int[] position = new int[2];
    while (y <= 7) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y++;
    }
    return null;
  }

  protected int[] cChipsRight (int color, int x, int y) {
    int[] position = new int[2];
    while (x <= 7) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      x++;
    }
    return null;
  }

  protected int[] cChipsLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (x >= 0) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      x--;
    }
    return null;
  }

  protected int[] cChipsDiagUpLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (y >= 0 && x >= 0) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y--;
      x--;
    }
    return null;
  }

  protected int[] cChipsDiagUpRight (int color, int x, int y) {
    int[] position = new int[2];
    while (y >= 0 && x <= 7) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y--;
      x++;
    }
    return null;
  }

  protected int[] cChipsDiagDownLeft (int color, int x, int y) {
    int[] position = new int[2];
    while (y <= 7 && x >= 0) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y++;
      x--;
    }
    return null;
  }

  protected int[] cChipsDiagDownRight (int color, int x, int y) {
    int[] position = new int[2];
    while (y <= 7 && x <= 7) {
      if (playerBoard.getItem(x, y) == color) {
        position[0] = x;
        position[1] = y;
        return position;
      }
      y++;
      x++;
    }
    return null;
  }

  //connectedChips finds all chips of the same color that are connected to the chip using the 
  //cChip functions and returns them as an array of chip coordinates.
  //@params  color - the color of the chip (black or white)
  //         x - the location of the chip on the gameboard (x-axis)
  //         y - the location of the chip on the gameboard (y-axis)
  //@returns an array of all the connected chip positions

  protected int[][] connectedChips(int color, int x, int y) {
    int[][] connected = new int[8][2];
    int index = 0;
    if (cChipsLeft(color, x, y) != null) {
      connected[0] = cChipsLeft(color, x, y);
    }
    if (cChipsRight(color, x, y) != null) {
      connected[1] = cChipsLeft(color, x, y);
    }
    if (cChipsDown(color, x, y) != null) {
      connected[2] = cChipsLeft(color, x, y);
    }
    if (cChipsUp(color, x, y) != null) {
      connected[3] = cChipsLeft(color, x, y);
    }
    if (cChipsDiagDownRight(color, x, y) != null) {
      connected[4] = cChipsLeft(color, x, y);
    }
    if (cChipsDiagDownLeft(color, x, y) != null) {
      connected[5] = cChipsLeft(color, x, y);
    }
    if (cChipsDiagUpRight(color, x, y) != null) {
      connected[6] = cChipsLeft(color, x, y);
    }
    if (cChipsDiagUpLeft(color, x, y) != null) {
      connected[7] = cChipsLeft(color, x, y);
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
  public boolean inGoal(int color, int x, int y) {
    if (color == WHITE && y == 0 || y == 7) {
      return true;
    } else if (color == BLACK && x == 0 || x == 7) {
      return true;
    }
    return false;
  }

  //Implemented by Nick
  //containsNetworks() evaluates a board and check's each chips list of connections to see if 
  //there is a Network of 6 or more chips. Uses an array to make sure that the same chip is not 
  //called more than once. Also keeps track of the direction of the chip so that two chips in a
  //line are not allowed to be in the same network. Makes sure the beginning and ending chips are
  //in the goal areas using inGoal().
  //@params color - the color of the player searching for a network
  //@returns true if there is a Network for the given player
  //         false if there are no valid Networks

  public int[] networkLengths (int color) {
    int[] allNetworks;
    int index = 0;
    for (int x = 0; x <= 7; x++) {
      for (int y = 0; y <= 7; y++) {
        if (playerBoard.getItem(x,y) == color) {
          allNetworks[index] = findConnections(color, x, y, 0, null, null);
          index++;
        }
      }
    }
    return allNetworks;
  }

  private int findConnections(int color, int x, int y, int total, int direction, int[][] previousChips) {
    
  }
  
   public boolean isValid (Move m, int color) {
        //No chip may be placed in any of the four corners
        if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 7))
            return false;

        //No chip may be placed in a goal of the opposite color
        if ((color == WHITE) && (m.y1 == 0 || m.y1 == 7)) 
            return false
        if ((color == BLACK) && (m.x1 == 0 || m.x1 == 7)) 
            return false

	//No chip may be placed in a square that is already occupied
      	if (pieces [m.x1][m.y1] != EMPTY)
            return false;

	//A player may not have more than two chips in a connected group, whether connected orthogonally or diagonally
        int [] adjacentPiece = checkAround (m.x1, m.y1);
        if (adjacentPiece [0] == -1 && adjacentPiece[1] == -1)
            return true;
        if (adjacentPiece [0] == -2 && adjacentPiece[1] == -2)
            return false;
        
        adjacentPiece = checkAround (adjacentPiece[0], adjacentPiece[1]);
        if (adjacentPiece [0] == -1 && adjacentPiece[1] == -1)
            return true;

        return false;
    }

    private int [] checkAround (int x, int y) {
	int [] position = new int [2];
        position [0] = -1;
        position [1] = -1;
        int count = 0;

	// Check all around position for pieces
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

                
                if (pieces[x+i][y+j] != EMPTY) {
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
