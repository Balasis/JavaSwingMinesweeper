package PathOpener;
// The logic here is to
//      1)Open surrounding minefield Buttons
//      2)In order to check which minefieldButtons to open (expand) compare to the
//         location of the button clicked we check 4 paths(TOP,BOTTOM,LEFT,RIGHT)
//      Now to fully cover all direction , TOP and BOTTOM checks their left and right on each line,
//      till they meet an indicator button or out of index
//      With the same logic Left and Right check their up and downs on each line they get.
//      check the code inside openTop,OpenBottom e.t.c


import Minefield.IndexAround;
import Minefield.IndexChecker;
import Minefield.MinefieldButton;

public class MainPathOpener {
    private MinefieldButton[][] minefield2DArray;
    private int indexA;
    private int indexB;
    private final IndexChecker indexChecker=new IndexChecker();
    private final IndexAround indexAround=new IndexAround();
    private final SquarePathOpener squarePathOpener=new SquarePathOpener();
    private final TopPathOpener topPathOpener=new TopPathOpener();
    private final BottomPathOpener bottomPathOpener=new BottomPathOpener();
    private final LeftPathOpener leftPathOpener=new LeftPathOpener();
    private final RightPathOpener rightPathOpener=new RightPathOpener();

    public MainPathOpener(){
    }

    public MainPathOpener(MinefieldButton[][] minefield2DArray, int indexA, int indexB){
    openPaths(minefield2DArray,indexA,indexB);
    }

    public void openPaths(MinefieldButton[][] minefield2DArray,int indexA,int indexB){
        this.minefield2DArray=minefield2DArray;
        this.indexA=indexA;
        this.indexB=indexB;

        squarePathOpener.openPath(minefield2DArray,indexA,indexB);
        topPathOpener.openPath(minefield2DArray,indexA,indexB);
        bottomPathOpener.openPath(minefield2DArray,indexA,indexB);
        leftPathOpener.openPath(minefield2DArray,indexA,indexB);
        rightPathOpener.openPath(minefield2DArray,indexA,indexB);
    }

}
