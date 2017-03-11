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
  
  public EPuzzleState moveVertical(Integer[][] puzzleState, int column, int oldRow, int newRow) {
	int tmp = puzzleState[newRow][column];
	puzzleState[newRow][column] = puzzleState[oldRow][column];
  	puzzleState[oldRow][column] = tmp;
  	return new EPuzzleState(puzzleState);
  }
  
  public EPuzzleState moveHorizontal(Integer[][] puzzleState, int row, int oldColumn, int newColumn) {
  	int tmp = puzzleState[row][newColumn];
	puzzleState[row][newColumn] = puzzleState[row][oldColumn];
  	puzzleState[row][oldColumn] = tmp;
  	return new EPuzzleState(puzzleState);
  }

   /**
  * getSuccessors
  * @param searcher - the current search
  */

  public ArrayList<SearchState> getSuccessors (Search searcher) {
    EPuzzleSearch psearcher = (EPuzzleSearch) searcher;
    Integer[][] puzzleState=psearcher.getPuzzleState();
    //TODO: Find the 0 here and save the index.
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

    ArrayList<EPuzzleState> epslis=new ArrayList<EPuzzleState>(); // the list of jugs states
    ArrayList<SearchState> slis=new ArrayList<SearchState>();

    //if (j1Space > 0) epslis.add(new EPuzzleState(cap1,j2)); //fill jug1

    EPuzzleState moveState;
    Integer[][] sourceState = puzzleState.clone();
    if (zRow < 2) {
    	moveState = moveVertical(sourceState, zColumn, zRow+1, zRow); //swamp downward
    	epslis.add(new EPuzzleState(moveState.get_state()));
    }
    sourceState = puzzleState.clone();
    if (zRow > 0 ) {
    	moveState = moveVertical(sourceState, zColumn, zRow-1, zRow); ///swap upward
    	epslis.add(new EPuzzleState(moveState.get_state()));
    }
    sourceState = puzzleState.clone();
    if (zColumn < 2) {
    	moveState = moveHorizontal(sourceState, zRow, zColumn+1, zColumn); //swap to the right
    	epslis.add(new EPuzzleState(moveState.get_state()));
    }
    sourceState = puzzleState.clone();
    if (zColumn > 0 ) {
    	moveState = moveHorizontal(sourceState, zRow, zColumn-1, zColumn); //swap to the left
    	epslis.add(new EPuzzleState(moveState.get_state()));
    }

    // cast the puzzle states as search states in slis

    for (EPuzzleState eps:epslis)slis.add((SearchState)eps);


    return slis;

  }

  /**
  * sameState - do 2 JugsSearchNodes have the same state?
  * @param s2 second state
  */

  public boolean sameState (SearchState s2) {
    EPuzzleState eps2 = (EPuzzleState) s2;

    return (puzzleState==eps2.get_state());
    }


  /**
  * toString
  */
  public String toString () {
	StringBuilder displayGrid = new StringBuilder();
	displayGrid.append("\n");
	for (Integer[] row: puzzleState) {
		for (int value: row) {
			displayGrid.append(value);
		}
		displayGrid.append("\n");
	}
    return displayGrid.toString();
    }



}



