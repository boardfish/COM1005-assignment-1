/**
 * EPuzzleState.java
 * State in an 8-puzzle problem
 * March 2017, undying-fish
 */

import java.util.*;

public class EPuzzleState extends SearchState {

private Integer[][] puzzleState;   //puzzle state

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

public Integer[][] get_state() {
        return puzzleState;
};

/**
 * goalP
 * @param searcher - the current search
 */

public boolean goalP(Search searcher) {
        EPuzzleSearch psearcher = (EPuzzleSearch) searcher;
        Integer[][] tar=psearcher.getTarget(); // get target state
        return (Arrays.deepEquals(puzzleState, tar));
}

/**
 * deepCopy
 * @param puzzleState - the state the returned value will be a deep copy of
 */

public EPuzzleState deepCopy (EPuzzleState puzzleState) {
        Integer[][] puzzleStateGrid = puzzleState.get_state();
        Integer[][] newPuzzleStateGrid = new Integer[3][3];
        for (int i = 0; i<3; i++) {
                newPuzzleStateGrid[i] = puzzleStateGrid[i].clone();
        }
        return new EPuzzleState(newPuzzleStateGrid);
}

/**
 * deepCopy
 * @param puzzleState - the grid the returned value will be a deep copy of
 */

public Integer[][] deepCopy (Integer[][] puzzleState) {
        Integer[][] newPuzzleStateGrid = new Integer[3][3];
        for (int i = 0; i<3; i++) {
                newPuzzleStateGrid[i] = puzzleState[i].clone();
        }
        return newPuzzleStateGrid;
}

/**
 * moveVertical
 * @param pS - the state to be edited by the move
 * @param column - the column in which the value to be moved resides
 * @param oldRow - the row in whch the value to be moved resides
 * @param newRow - the row to swap the new value into
 */

public EPuzzleState moveVertical(Integer[][] pS, int column, int oldRow, int newRow) {
        Integer[][] puzzleState = deepCopy(pS);
        int tmp = puzzleState[newRow][column];
        puzzleState[newRow][column] = puzzleState[oldRow][column];
        puzzleState[oldRow][column] = tmp;
        return new EPuzzleState(puzzleState);
}

/**
 * moveHorizontal
 * @param pS - the state to be edited by the move
 * @param row - the row in which the value to be moved resides
 * @param oldColumn - the column in whch the value to be moved resides
 * @param newColumn - the column to swap the new value into
 */

public EPuzzleState moveHorizontal(Integer[][] pS, int row, int oldColumn, int newColumn) {
        Integer[][] puzzleState = deepCopy(pS);
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
        Integer[][] clonePuzzleState = deepCopy(puzzleState);

        //Find the location of the 0 in the grid, assuming it exists
        boolean zeroFound = false;
        int zColumn = -1;
        int zRow = -1;
        for (Integer[] row : puzzleState) {
                for (int value : row) {
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

        if (zRow < 2) { //if the 0 is in the lower rows
                epslis.add(new EPuzzleState(moveVertical(clonePuzzleState, zColumn, zRow+1, zRow)));
                //add a new state in which a tile adjacent to 0 is moved upward into that position
        }
        if (zRow > 0 ) {
                epslis.add(new EPuzzleState(moveVertical(clonePuzzleState, zColumn, zRow-1, zRow)));
                //add a new state in which a tile adjacent to 0 is moved downward into that position
        }
        if (zColumn < 2) {
                epslis.add(new EPuzzleState(moveHorizontal(clonePuzzleState, zRow, zColumn+1, zColumn)));
                //add a new state in which a tile adjacent to 0 is moved to its left into that position
        }
        if (zColumn > 0 ) {
                epslis.add(new EPuzzleState(moveHorizontal(clonePuzzleState, zRow, zColumn-1, zColumn)));
                //add a new state in which a tile adjacent to 0 is moved to its right into that position
        }
        // cast the puzzle states as search states in slis
        for (EPuzzleState eps : epslis) slis.add((SearchState)eps);

        return slis;

}

/**
 * getSuccessors
 * @param obj - object for which equality is to be checked
 */

public boolean equals (Object obj)
{
        if (this==obj) return true;  //references same?
        if (this == null) return false;  //is null?
        if (this.getClass() != obj.getClass()) return false;  //same class?
        EPuzzleState eps = (EPuzzleState) obj; //cast object to EPuzzleState
        boolean equalityCheck = true; //will only change if false
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
 * sameState - do 2 EPuzzleSearch nodes have the same state?
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
        displayGrid.append("|");
        for (Integer[] row : puzzleState) {
                for (int value : row) {
                        displayGrid.append(value);
                }
                displayGrid.append("|");
        }
        return displayGrid.toString();
}



}
