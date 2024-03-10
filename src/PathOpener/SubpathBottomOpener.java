package PathOpener;

import AppStart.IndexAround;
import AppStart.IndexChecker;
import AppStart.MinefieldButton;

public class SubpathBottomOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();

    public SubpathBottomOpener() {
    }

    public SubpathBottomOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentBottomIndexA = indexA + 1;
        boolean BottomButtonExists = indexChecker.isValidIndex(minefield2DArray, currentBottomIndexA, indexB);
        boolean isItAnIndicator = BottomButtonExists ? minefield2DArray[currentBottomIndexA][indexB].isAnIndicator() : false;

        while (BottomButtonExists && !isItAnIndicator) {
            minefield2DArray[currentBottomIndexA][indexB].setRevealed(true);
            if (minefield2DArray[currentBottomIndexA][indexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentBottomIndexA, indexB);
            }
            currentBottomIndexA++;
            BottomButtonExists = indexChecker.isValidIndex(minefield2DArray, currentBottomIndexA, indexB);
            isItAnIndicator = BottomButtonExists ? minefield2DArray[currentBottomIndexA][indexB].isAnIndicator() : false;
        }
    }
}
