package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class SubpathTopOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();

    public SubpathTopOpener() {
    }

    public SubpathTopOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentTopIndexA = indexA - 1;
        boolean TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentTopIndexA, indexB);
        boolean isItAnIndicator = TopButtonExists ? minefield2DArray[currentTopIndexA][indexB].isAnIndicator() : false;

        while (TopButtonExists && !isItAnIndicator) {
            minefield2DArray[currentTopIndexA][indexB].setRevealed(true);
            if (minefield2DArray[currentTopIndexA][indexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentTopIndexA, indexB);
            }
            currentTopIndexA--;
            TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentTopIndexA, indexB);
            isItAnIndicator = TopButtonExists ? minefield2DArray[currentTopIndexA][indexB].isAnIndicator() : false;
        }
    }
}
