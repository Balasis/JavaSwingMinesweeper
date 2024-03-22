package minefield;

public class IndexChecker {
    private boolean isTopLeftExist;
    private boolean isTopExist;
    private boolean isTopRightExist;
    private boolean isLeftExist;
    private boolean isRightExist;
    private boolean isBottomLeftExist;
    private boolean isBottomExist;
    private boolean isBottomRightExist;

    public IndexChecker(){
    }

    public IndexChecker(MinefieldButton[][] minefield2DArray, int indexA, int indexB){
        checkSurroundingIndexes(minefield2DArray,indexA,indexB);
    }

    public void checkSurroundingIndexes(MinefieldButton[][] minefield2DArray, int indexA, int indexB){
        //resetInCase user used the parameterized constructor for other reason.
        isTopLeftExist=false;
        isTopExist=false;
        isTopRightExist=false;
        isLeftExist=false;
        isRightExist=false;
        isBottomLeftExist=false;
        isBottomExist=false;
        isBottomRightExist=false;

        if (indexA - 1 >= 0 && indexB - 1 >= 0) {this.isTopLeftExist = true;}// Top left
        if (indexA - 1 >= 0) {this.isTopExist = true;} // Top
        if (indexA - 1 >= 0 && indexB + 1 < minefield2DArray[0].length) {this.isTopRightExist = true;} // Top right
        if (indexB - 1 >= 0) {this.isLeftExist = true;}// Left
        if (indexB + 1 < minefield2DArray[0].length) {this.isRightExist = true;} // Right
        if (indexA + 1 < minefield2DArray.length && indexB - 1 >= 0) {this.isBottomLeftExist = true;}  // Bottom left
        if (indexA + 1 < minefield2DArray.length) {this.isBottomExist = true;} // Bottom
        if (indexA + 1 < minefield2DArray.length && indexB + 1 < minefield2DArray[0].length) {this.isBottomRightExist = true;} // Bottom right
    }

    //Instant check instead of passing to parameter
    public boolean isValidIndex(MinefieldButton[][] minefield2DArray,int indexA,int indexB) {
        return indexA >= 0 && indexB >= 0 && indexA < minefield2DArray.length && indexB < minefield2DArray[0].length;
    }

    public boolean isTopLeftExist() {
        return isTopLeftExist;
    }

    public boolean isTopExist() {
        return isTopExist;
    }

    public boolean isTopRightExist() {
        return isTopRightExist;
    }

    public boolean isLeftExist() {
        return isLeftExist;
    }

    public boolean isRightExist() {
        return isRightExist;
    }

    public boolean isBottomLeftExist() {
        return isBottomLeftExist;
    }

    public boolean isBottomExist() {
        return isBottomExist;
    }

    public boolean isBottomRightExist() {
        return isBottomRightExist;
    }

}
