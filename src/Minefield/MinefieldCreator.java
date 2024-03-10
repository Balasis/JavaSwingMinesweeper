package Minefield;

import AppStart.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class MinefieldCreator{
    private final List<GameObserver> observers = new ArrayList<>();
    private final MinefieldButton[][] minefieldJButtons;
    private int numberOfMines;
    private final int squareDimensions;

    public MinefieldCreator(int numberOfMines,int squareDimensions){
        this.numberOfMines=numberOfMines;
        this.squareDimensions=squareDimensions;
        minefieldJButtons= createMinefield2dArray();//creates a 2d (square) field of MinefieldButtons(extend JButton class)
        addMinesToTheMinefield(minefieldJButtons);//add mines to random locations
        setIndicationNumbers(minefieldJButtons);//find and set number of surround mines of each Minefield Button.

    }

    private MinefieldButton[][] createMinefield2dArray(){
        MinefieldButton[][] minefieldArray=new MinefieldButton[squareDimensions][squareDimensions];
        for (int i = 0; i < minefieldArray.length; i++) {
            for (int j = 0; j < minefieldArray[0].length; j++) {
                minefieldArray[i][j]=new MinefieldButton();
            }
        }
        return minefieldArray;
    }

    private void addMinesToTheMinefield(MinefieldButton[][] minefield2DArray){
        while(numberOfMines>0){
            MinefieldButton mfB=minefield2DArray[randomIntNumber(0,31)][randomIntNumber(0,31)];
            if (!mfB.isAMine()){// checks first if there is already a mine at the current location
                mfB.setAMine(true);
                numberOfMines--;
            }
        }
    }

    private int randomIntNumber(int min,int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    private void setIndicationNumbers(MinefieldButton[][] minefield2DArray){
        int surroundingMines;
        for (int i = 0; i < minefield2DArray.length; i++) {
            for (int j = 0; j < minefield2DArray[0].length; j++) {
                if (!minefield2DArray[i][j].isAMine()){
                    surroundingMines=findSurroundingMinesNum(minefield2DArray,i,j);
                    if (surroundingMines!=0){
                        minefield2DArray[i][j].setAnIndicator(true);
                        minefield2DArray[i][j].setIndicatorNumber(surroundingMines);
                    }
                }
            }
        }
    }

    private int findSurroundingMinesNum(MinefieldButton[][] minefield2DArray, int indexA, int indexB) {
        IndexChecker check = new IndexChecker(minefield2DArray, indexA, indexB);
        IndexAround index = new IndexAround(indexA, indexB);
        int surroundingMines = 0;

        // Check each neighboring cell for a mine
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getTopLeftIndexA(), index.getTopLeftIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getTopIndexA(), index.getTopIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getTopRightIndexA(), index.getTopRightIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getLeftIndexA(), index.getLeftIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getRightIndexA(), index.getRightIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getBottomLeftIndexA(), index.getTopLeftIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getBottomIndexA(), index.getBottomIndexB());
        surroundingMines += checkAndIncrementIfMine(minefield2DArray, index.getBottomRightIndexA(), index.getBottomRightIndexB());

        return surroundingMines;
    }

    // Helper method to check if the cell contains a mine and increment surroundingMines accordingly
    private int checkAndIncrementIfMine(MinefieldButton[][] minefield2DArray, int rowIndex, int colIndex) {
        if (isValidIndex(minefield2DArray, rowIndex, colIndex) && minefield2DArray[rowIndex][colIndex].isAMine()) {
            return 1;
        }
        return 0;
    }

    // Helper method to check if the given index is within the bounds of the array
    private boolean isValidIndex(MinefieldButton[][] minefield2DArray, int rowIndex, int colIndex) {
        return rowIndex >= 0 && rowIndex < minefield2DArray.length &&
                colIndex >= 0 && colIndex < minefield2DArray[0].length;
    }

    public MinefieldButton[][] getMinefieldJButtons() {
        return minefieldJButtons;
    }




}
