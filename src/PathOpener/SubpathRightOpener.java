package PathOpener;

import AppStart.IndexAround;
import AppStart.IndexChecker;
import AppStart.MinefieldButton;

public class SubpathRightOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker=new IndexChecker();
    private final IndexAround indexAround=new IndexAround();
    private final SquarePathOpener squarePathOpener=new SquarePathOpener();

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
            if ( minefield2DArray[indexA][currentRightIndexB].isEmpty()){
            System.out.println( " [ "+indexA +" , "+ currentRightIndexB+" ]");
                squarePathOpener.openPath(minefield2DArray,indexA,currentRightIndexB);
            }
            currentRightIndexB++;
            RightButtonExists= indexChecker.isValidIndex(minefield2DArray,indexA,currentRightIndexB);
            isItAnIndicator = RightButtonExists ? minefield2DArray[indexA][currentRightIndexB].isAnIndicator() : false;
        }
    }


}
