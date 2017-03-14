/**
* JugsState.java
* State in a jugs problem
* 2013 version
*/

import java.util.*;

public class EPuzzleState extends SearchState{

  private Integer[][] puzzleState; //puzzle state

  /**
  * constructor
  * @param pS puzzle state
  */

  public EPuzzleState(Integer[][] pS){
	  puzzleState=pS;
  }
  
  public EPuzzleState(EPuzzleState original) {
	  puzzleState = original.get_state();
  }

  /**
  * accessor for current state of puzzle
  */

  public Integer[][] get_state() {return puzzleState;};

  /**
  * goalP
  * @param searcher - the current search
  */

  public boolean goalP(Search searcher) {
    EPuzzleSearch psearcher = (EPuzzleSearch) searcher;
    Integer[][] tar=psearcher.getTarget(); // get target state
    return (Arrays.deepEquals(puzzleState, tar));
  }
  
  public EPuzzleState moveVertical(Integer[][] pS, int column, int oldRow, int newRow) {
	Integer[][] puzzleState = deepCopy(pS);
	int tmp = puzzleState[newRow][column];
	puzzleState[newRow][column] = puzzleState[oldRow][column];
  	puzzleState[oldRow][column] = tmp;
  	System.out.println("Resultant state was " + new EPuzzleState(puzzleState) + "!");
  	return new EPuzzleState(puzzleState);
  }
  
  public EPuzzleState moveHorizontal(Integer[][] pS, int row, int oldColumn, int newColumn) {
	Integer[][] puzzleState = deepCopy(pS);
	System.out.println("Passed state was " + new EPuzzleState(puzzleState) + "!");
	int tmp = puzzleState[row][newColumn];
	puzzleState[row][newColumn] = puzzleState[row][oldColumn];
  	puzzleState[row][oldColumn] = tmp;
  	System.out.println("Resultant state was " + new EPuzzleState(puzzleState) + "!");
  	return new EPuzzleState(puzzleState);
  }
  
  public EPuzzleState deepCopy (EPuzzleState puzzleState) {
	  Integer[][] puzzleStateGrid = puzzleState.get_state();
	  Integer[][] newPuzzleStateGrid = new Integer[3][3];
	  for (int i = 0; i<3; i++) {
			newPuzzleStateGrid[i] = puzzleStateGrid[i].clone();
	  }
	  return new EPuzzleState(newPuzzleStateGrid);
  }
  
  public Integer[][] deepCopy (Integer[][] puzzleState) {
	  Integer[][] newPuzzleStateGrid = new Integer[3][3];
	  for (int i = 0; i<3; i++) {
			newPuzzleStateGrid[i] = puzzleState[i].clone();
	  }
	  return newPuzzleStateGrid;
  }

   /**
  * getSuccessors
  * @param searcher - the current search
  */

  public ArrayList<SearchState> getSuccessors (Search searcher) {
    EPuzzleSearch psearcher = (EPuzzleSearch) searcher;
    Integer[][] clonePuzzleState = deepCopy(this.get_state());

    StringBuilder displayGrid = new StringBuilder();
	for (Integer[] row: clonePuzzleState) {
		for (int value: row) {
			displayGrid.append(value);
		}
		displayGrid.append("|");
	}
	System.out.println("CURRENT STATE:");
	System.out.println(displayGrid.toString());
    
    //Find the 0 here and save the indexes
    boolean zeroFound = false;
    int zColumn = -1;
    int zRow = -1;
	for (Integer[] row: puzzleState) {
		for (int value: row) {
			if (value == 0) {
				zColumn = Arrays.asList(row).indexOf(value);
				zeroFound = true;
			}
		}
		if (zeroFound) {
			zRow = Arrays.asList(puzzleState).indexOf(row);
			break;
		}
	}

    ArrayList<EPuzzleState> epslis=new ArrayList<EPuzzleState>(); // the list of 8 puzzle states
    ArrayList<SearchState> slis=new ArrayList<SearchState>();

    //if (j1Space > 0) epslis.add(new EPuzzleState(cap1,j2)); //fill jug1

    if (zRow < 2) {
    	System.out.println("Moving up!");
    	epslis.add(new EPuzzleState(moveVertical(clonePuzzleState, zColumn, zRow+1, zRow)));
    }
    if (zRow > 0 ) {
    	System.out.println("Moving down!");
    	epslis.add(new EPuzzleState(moveVertical(clonePuzzleState, zColumn, zRow-1, zRow)));
    }
    if (zColumn < 2) {
    	System.out.println("Moving left!");
    	epslis.add(new EPuzzleState(moveHorizontal(clonePuzzleState, zRow, zColumn+1, zColumn)));
    }
    if (zColumn > 0 ) {
    	System.out.println("Moving right!");
    	epslis.add(new EPuzzleState(moveHorizontal(clonePuzzleState, zRow, zColumn-1, zColumn))); 	
    }
    // cast the puzzle states as search states in slis
    for (EPuzzleState eps:epslis)slis.add((SearchState)eps);

    return slis;

  }

  public boolean equals (Object obj)
  {
   if (this==obj) return true;
   if (this == null) return false;
   if (this.getClass() != obj.getClass()) return false;
   EPuzzleState eps = (EPuzzleState) obj ;
   boolean equalityCheck = true;
   for (int i = 0; i<this.puzzleState.length; i++) {
	   for (int j = 0; j<this.puzzleState[i].length; j++) {
		   if (this.puzzleState[i][j] != eps.get_state()[i][j]) {
			   equalityCheck = false;
			   break;
		   }
	   }
   }
   return equalityCheck;
   }
  
  /**
  * sameState - do 2 JugsSearchNodes have the same state?
  * @param s2 second state
  */

  public boolean sameState (SearchState s2) {
    EPuzzleState eps2 = (EPuzzleState) s2;
    return (this.equals(eps2));
    }


  /**
  * toString
  */
  public String toString () {
	StringBuilder displayGrid = new StringBuilder();
	for (Integer[] row: puzzleState) {
		for (int value: row) {
			displayGrid.append(value);
		}
		displayGrid.append("|");
	}
    return displayGrid.toString();
    }



}



