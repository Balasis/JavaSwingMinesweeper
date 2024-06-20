package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class BottomPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();
    private final SubpathRightOpener subpathRightOpener = new SubpathRightOpener();
    private final SubpathLeftOpener subpathLeftOpener = new SubpathLeftOpener();

    public BottomPathOpener() {
    }

    public BottomPathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentBottomIndexA = indexA + 1;
        int currentBottomIndexB = indexB;
        boolean TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentBottomIndexA, currentBottomIndexB);
        boolean isItAnIndicator = TopButtonExists ? minefield2DArray[currentBottomIndexA][currentBottomIndexB].isAnIndicator() : false;

        while (TopButtonExists && !isItAnIndicator) {
            minefield2DArray[currentBottomIndexA][currentBottomIndexB].setRevealed(true);
            if (minefield2DArray[currentBottomIndexA][currentBottomIndexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentBottomIndexA, currentBottomIndexB);
            }
            subpathLeftOpener.openPath(minefield2DArray, currentBottomIndexA, currentBottomIndexB);
            subpathRightOpener.openPath(minefield2DArray, currentBottomIndexA, currentBottomIndexB);

            currentBottomIndexA++;
            TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentBottomIndexA, currentBottomIndexB);
            isItAnIndicator = TopButtonExists ? minefield2DArray[currentBottomIndexA][currentBottomIndexB].isAnIndicator() : false;
        }
    }


}