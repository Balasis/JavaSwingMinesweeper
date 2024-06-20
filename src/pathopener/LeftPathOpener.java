package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class LeftPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();
    private final SubpathTopOpener subpathTopOpener = new SubpathTopOpener();
    private final SubpathBottomOpener subpathBottomOpener = new SubpathBottomOpener();

    public LeftPathOpener() {
    }

    public LeftPathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentLeftIndexA = indexA;
        int currentLeftIndexB = indexB - 1;
        boolean LeftButtonExists = indexChecker.isValidIndex(minefield2DArray, currentLeftIndexA, currentLeftIndexB);
        boolean isItAnIndicator = LeftButtonExists ? minefield2DArray[currentLeftIndexA][currentLeftIndexB].isAnIndicator() : false;

        while (LeftButtonExists && !isItAnIndicator) {
            minefield2DArray[currentLeftIndexA][currentLeftIndexB].setRevealed(true);
            if (minefield2DArray[currentLeftIndexA][currentLeftIndexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentLeftIndexA, currentLeftIndexB);
            }

            subpathTopOpener.openPath(minefield2DArray, currentLeftIndexA, currentLeftIndexB);
            subpathBottomOpener.openPath(minefield2DArray, currentLeftIndexA, currentLeftIndexB);

            currentLeftIndexB--;
            LeftButtonExists = indexChecker.isValidIndex(minefield2DArray, currentLeftIndexA, currentLeftIndexB);
            isItAnIndicator = LeftButtonExists ? minefield2DArray[currentLeftIndexA][currentLeftIndexB].isAnIndicator() : false;
        }


    }
}