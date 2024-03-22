package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class RightPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();
    private final SubpathTopOpener subpathTopOpener = new SubpathTopOpener();
    private final SubpathBottomOpener subpathBottomOpener = new SubpathBottomOpener();

    public RightPathOpener() {
    }

    public RightPathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentRightIndexA = indexA;
        int currentRightIndexB = indexB + 1;
        boolean RightButtonExists = indexChecker.isValidIndex(minefield2DArray, currentRightIndexA, currentRightIndexB);
        boolean isItAnIndicator = RightButtonExists ? minefield2DArray[currentRightIndexA][currentRightIndexB].isAnIndicator() : false;

        while (RightButtonExists && !isItAnIndicator) {
            minefield2DArray[currentRightIndexA][currentRightIndexB].setRevealed(true);
            if (minefield2DArray[currentRightIndexA][currentRightIndexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentRightIndexA, currentRightIndexB);
            }

            subpathTopOpener.openPath(minefield2DArray, currentRightIndexA, currentRightIndexB);
            subpathBottomOpener.openPath(minefield2DArray, currentRightIndexA, currentRightIndexB);

            currentRightIndexB++;
            RightButtonExists = indexChecker.isValidIndex(minefield2DArray, currentRightIndexA, currentRightIndexB);
            isItAnIndicator = RightButtonExists ? minefield2DArray[currentRightIndexA][currentRightIndexB].isAnIndicator() : false;
        }
    }
}
