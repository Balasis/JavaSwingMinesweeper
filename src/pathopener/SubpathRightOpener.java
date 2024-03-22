package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class SubpathRightOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker=new IndexChecker();
    private final IndexAround indexAround=new IndexAround();
    private final SquarePathOpener squarePathOpener=new SquarePathOpener();
     //afraid of infinite recursion ,I just expand the cases
    private final SubpathTopOpener subpathTopOpener=new SubpathTopOpener();
    private final SubpathBottomOpener subpathBottomOpener=new SubpathBottomOpener();
    public SubpathRightOpener(){

    }

    public SubpathRightOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray,indexA,indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB){
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentRightIndexB=indexB+1;
        boolean RightButtonExists=indexChecker.isValidIndex(minefield2DArray,indexA,currentRightIndexB);
        boolean isItAnIndicator = RightButtonExists ? minefield2DArray[indexA][currentRightIndexB].isAnIndicator() : false;
        while(RightButtonExists && !isItAnIndicator){


            minefield2DArray[indexA][currentRightIndexB].setRevealed(true);
            subpathTopOpener.openPath(minefield2DArray, indexA, currentRightIndexB);
            subpathBottomOpener.openPath(minefield2DArray, indexA, currentRightIndexB);

            if ( minefield2DArray[indexA][currentRightIndexB].isEmpty()){
                squarePathOpener.openPath(minefield2DArray,indexA,currentRightIndexB);
            }
            currentRightIndexB++;
            RightButtonExists= indexChecker.isValidIndex(minefield2DArray,indexA,currentRightIndexB);
            isItAnIndicator = RightButtonExists ? minefield2DArray[indexA][currentRightIndexB].isAnIndicator() : false;
        }
    }


}
