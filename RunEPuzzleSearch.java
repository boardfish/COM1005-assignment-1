 /**
   * Run_JugsSearch.java
   *
   *
   * 2013 version
   *
   * @author <a href="mailto: "Phil Green</a>
   * @version 1

   run a jugs search

 */

import sheffield.*;
import java.util.*;


public class RunEPuzzleSearch {

public static void main(String[] arg) {

    // create an EasyWriter

    EasyWriter screen = new EasyWriter();
    Integer[][] initStateGrid = {{2,4,7},{1,3,6},{5,8,0}};
    Integer[][] targetGrid = {{1,2,3},{4,5,6},{7,8,0}};
    EPuzzleSearch searcher = new EPuzzleSearch(initStateGrid,targetGrid);
    SearchState initState = (SearchState) new EPuzzleState(initStateGrid);

	//change from search1 - specify strategy
    //String resb = searcher.runSearch(initState, "breadthFirst");
    //screen.println(resb);
    String resd = searcher.runSearch(initState, "depthFirst");
    screen.println(resd);

}
}
