package pathopener;

import minefield.IndexAround;
import minefield.IndexChecker;
import minefield.MinefieldButton;

public class TopPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker = new IndexChecker();
    private final IndexAround indexAround = new IndexAround();
    private final SquarePathOpener squarePathOpener = new SquarePathOpener();
    private final SubpathRightOpener subpathRightOpener = new SubpathRightOpener();
    private final SubpathLeftOpener subpathLeftOpener = new SubpathLeftOpener();

    public TopPathOpener() {
    }

    public TopPathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        openPath(minefield2DArray, indexA, indexB);
    }

    public void openPath(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        this.minefield2DArray = minefield2DArray;
        this.indexA = indexA;
        this.indexB = indexB;
        int currentTopIndexA = indexA - 1;
        int currentTopIndexB = indexB;
        boolean TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentTopIndexA, currentTopIndexB);
        boolean isItAnIndicator = TopButtonExists ? minefield2DArray[currentTopIndexA][currentTopIndexB].isAnIndicator() : false;

        while (TopButtonExists && !isItAnIndicator) {
            minefield2DArray[currentTopIndexA][currentTopIndexB].setRevealed(true);
            if (minefield2DArray[currentTopIndexA][currentTopIndexB].isEmpty()) {
                squarePathOpener.openPath(minefield2DArray, currentTopIndexA, currentTopIndexB);
            }


            subpathRightOpener.openPath(minefield2DArray, currentTopIndexA, currentTopIndexB);
            subpathLeftOpener.openPath(minefield2DArray, currentTopIndexA, currentTopIndexB);

            currentTopIndexA--;
            TopButtonExists = indexChecker.isValidIndex(minefield2DArray, currentTopIndexA, currentTopIndexB);
            isItAnIndicator = TopButtonExists ? minefield2DArray[currentTopIndexA][currentTopIndexB].isAnIndicator() : false;
        }
    }


}