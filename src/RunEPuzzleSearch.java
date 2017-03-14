/**
 * RunEPuzzleSearch.java
 *
 *
 * March 2017 version

   run an 8-PUZZLE search

 */

import sheffield.*;
import java.util.*;


public class RunEPuzzleSearch {

public static void main(String[] arg) {

        // create an EasyWriter

        EasyWriter screen = new EasyWriter();

        //set up grids

        Integer[][] p1 = {{1,0,3},{4,2,6},{7,5,8}};
        Integer[][] p2 = {{4,1,3},{7,2,5},{0,8,6}};
        Integer[][] p3 = {{2,3,6},{1,5,8},{4,7,0}};
        Integer[][] targetGrid = {{1,2,3},{4,5,6},{7,8,0}};

        //run search for P1

        EPuzzleSearch searcher = new EPuzzleSearch(p1,targetGrid);
        SearchState p1init = (SearchState) new EPuzzleState(p1);
        String resb1 = searcher.runSearch(p1init, "breadthFirst");
        screen.println(resb1);

        //run search for P2

        EPuzzleSearch searcher2 = new EPuzzleSearch(p2,targetGrid);
        SearchState p2init = (SearchState) new EPuzzleState(p2);
        String resb2 = searcher.runSearch(p2init, "breadthFirst");
        screen.println(resb2);

        //run search for P3

        EPuzzleSearch searcher3 = new EPuzzleSearch(p3,targetGrid);
        SearchState p3init = (SearchState) new EPuzzleState(p3);
        String resb3 = searcher.runSearch(p3init, "breadthFirst");
        screen.println(resb3);

        //I executed all three searces with one run of the program, and outputted
        //the results to text files pXoutput.txt where x is in the range (1..3).

        //change from search1 - specify strategy
        //String resd = searcher.runSearch(initState, "depthFirst");
        //screen.println(resd);

}
}
