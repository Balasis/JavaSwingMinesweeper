// The logic here is to
//      1)Open surrounding minefield Buttons
//      2)In order to check which minefieldButtons to open (expand) compare to the
//         location of the button clicked we check 4 paths(TOP,BOTTOM,LEFT,RIGHT)
//      Now to fully cover all direction , TOP and BOTTOM checks their left and right on each line,
//      till they meet an indicator button or out of index
//      With the same logic Left and Right check their up and downs on each line they get.
//      check the code inside openTop,OpenBottom e.t.c


public class MinefieldPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    //Checks if the index exist in order to do processes with it
    private final IndexChecker indexChecker=new IndexChecker();
    //It give us the square indexes positions.
    // e.g TopLeft would be [i-1][j-1]Related to the current
    private final IndexAround indexAround=new IndexAround();


    public MinefieldPathOpener(){
    }

    public MinefieldPathOpener(MinefieldButton[][] minefield2DArray,int indexA,int indexB){
    this.minefield2DArray=minefield2DArray;
    this.indexA=indexA;
    this.indexB=indexB;
    openPaths();
    }

    public void pathOpen(MinefieldButton[][] minefield2DArray,int indexA,int indexB){
        this.minefield2DArray=minefield2DArray;
        this.indexA=indexA;
        this.indexB=indexB;
        openPaths();
    }

    private void openPaths(){

        openSelf();
        openSurroundingSquare();
        openTopPaths();
//        openRightPaths();
//        openBottomPaths();
//        openLeftPaths();
    }

    private void openSelf(){
        minefield2DArray[indexA][indexB].setRevealed(true);
    }

    private void openSurroundingSquare(){
        int[][] relativePositions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        //I add indexA to the corresponding index change compare to the location
        //This way we form the new indexA and indexB and been sent to index checker
        //along with the minefieldArray to see if exists.If exists then setting the
        //revealed boolean to true
        for (int[] relativePosition:relativePositions){
           int currentIndexA=indexA + relativePosition[0];
           int currentIndexB=indexB + relativePosition[1];
           if (indexChecker.isValidIndex(minefield2DArray,currentIndexA,currentIndexB)){
               minefield2DArray[currentIndexA][currentIndexB].setRevealed(true);
           }
        }
    }


    private void openTopPaths(){
     int currentTopIndexA=indexA-1;
     int currentTopIndexB=indexB;
     boolean TopButtonExists=indexChecker.isValidIndex(minefield2DArray,currentTopIndexA,currentTopIndexB);
     boolean isItAnIndicator = TopButtonExists ? minefield2DArray[currentTopIndexA][currentTopIndexB].isAnIndicator() : false;

        while(TopButtonExists && !isItAnIndicator){
            minefield2DArray[currentTopIndexA][currentTopIndexB].setRevealed(true);
//            int currentleftIndexB=indexB-1;
//
//
//            int currentRightIndexB=indexB+1;

            currentTopIndexA--;
            TopButtonExists=indexChecker.isValidIndex(minefield2DArray,currentTopIndexA,currentTopIndexB);
            isItAnIndicator= TopButtonExists ? minefield2DArray[currentTopIndexA][currentTopIndexB].isAnIndicator() : false;
        }
    }







    private void openRightPaths(){

    }

    private void openBottomPaths(){

    }

    private void openLeftPaths(){

    }
}
