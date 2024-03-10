package PathOpener;

import AppStart.IndexAround;
import AppStart.IndexChecker;
import AppStart.MinefieldButton;

public class SubpathLeftOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker=new IndexChecker();
    private final IndexAround indexAround=new IndexAround();
    private final SquarePathOpener squarePathOpener=new SquarePathOpener();

    public SubpathLeftOpener(){
    }

    public SubpathLeftOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray,indexA,indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB){

        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentLeftIndexB=indexB-1;
        boolean LeftButtonExists=indexChecker.isValidIndex(minefield2DArray,indexA,currentLeftIndexB);
        boolean isItAnIndicator = LeftButtonExists ? minefield2DArray[indexA][currentLeftIndexB].isAnIndicator() : false;
        while(LeftButtonExists && !isItAnIndicator){

            minefield2DArray[indexA][currentLeftIndexB].setRevealed(true);

            if ( minefield2DArray[indexA][currentLeftIndexB].isEmpty()){

                squarePathOpener.openPath(minefield2DArray,indexA,currentLeftIndexB);
            }
            currentLeftIndexB--;
            LeftButtonExists= indexChecker.isValidIndex(minefield2DArray,indexA,currentLeftIndexB);
            isItAnIndicator = LeftButtonExists ? minefield2DArray[indexA][currentLeftIndexB].isAnIndicator() : false;
        }
    }
}
