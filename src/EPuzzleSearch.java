/**
*	EPuzzleSearch.java
*
*	search for 8 puzzle problems
*   2017 version
*/


//import Search;
//import SearchNode;

import java.util.*;

public class EPuzzleSearch extends Search {

    private Integer[][] puzzleState; //8 puzzle state
    private Integer[][] target; //target

    /** constructor  takes puzzle state and target
    * @param pS state of the puzzle at present
    * @param tar target state of puzzle
    */

    public EPuzzleSearch (Integer[][] pS, Integer[][] tar) {

	    puzzleState=pS;
	    target=tar;

    }

    /**
    * accessor for puzzle state
    */

    public Integer[][] getPuzzleState(){
	return puzzleState;
    }

    /**
    * accessor for target
    */

     public Integer[][] getTarget(){
	return target;
    }
}










