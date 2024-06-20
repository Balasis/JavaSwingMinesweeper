package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class SubpathLeftOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();
    //Afraid of recursion so i just extend a bit the cases
    private final SubpathTopOpener subpathTopOpener = new SubpathTopOpener();
    private final SubpathBottomOpener subpathBottomOpener = new SubpathBottomOpener();

    public SubpathLeftOpener() {
    }

    public SubpathLeftOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {

        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentLeftIndexB = indexB - 1;
        boolean LeftButtonExists = indexChecker.isValidIndex(minefield2DArray, indexA, currentLeftIndexB);
        boolean isItAnIndicator = LeftButtonExists ? minefield2DArray[indexA][currentLeftIndexB].isAnIndicator() : false;
        while (LeftButtonExists && !isItAnIndicator) {

            minefield2DArray[indexA][currentLeftIndexB].setRevealed(true);

            subpathTopOpener.openPath(minefield2DArray, indexA, currentLeftIndexB);
            subpathBottomOpener.openPath(minefield2DArray, indexA, currentLeftIndexB);

            if (minefield2DArray[indexA][currentLeftIndexB].isEmpty()) {

                squarePathOpener.openPath(minefield2DArray, indexA, currentLeftIndexB);
            }
            currentLeftIndexB--;
            LeftButtonExists = indexChecker.isValidIndex(minefield2DArray, indexA, currentLeftIndexB);
            isItAnIndicator = LeftButtonExists ? minefield2DArray[indexA][currentLeftIndexB].isAnIndicator() : false;
        }


    }
}
