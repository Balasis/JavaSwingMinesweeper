package PathOpener;

import Minefield.IndexAround;
import Minefield.IndexChecker;
import Minefield.MinefieldButton;

public class SquarePathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker=new IndexChecker();
    private final IndexAround indexAround=new IndexAround();

    public SquarePathOpener(){
    }

    public SquarePathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray,indexA,indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB){
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int[][] relativePositions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        //I add indexA to the corresponding index change compare to the location
        //This way we form the new indexA and indexB and been sent to index checker
        //along with the minefieldArray to see if exists.If exists then setting the
        //revealed boolean to true
        for (int[] relativePosition:relativePositions){
            int currentIndexA=indexA + relativePosition[0];
            int currentIndexB=indexB + relativePosition[1];

            if (indexChecker.isValidIndex(minefield2DArray,currentIndexA,currentIndexB)){           //
                minefield2DArray[currentIndexA][currentIndexB].setRevealed(true);

            }
        }
    }

}
