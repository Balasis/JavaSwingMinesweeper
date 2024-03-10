public class IndexAround {
    private int TopLeftIndexA;
    private int TopIndexA;
    private int TopRightIndexA;
    private int LeftIndexA;
    private int RightIndexA;
    private int BottomLeftIndexA;
    private int BottomIndexA;
    private int BottomRightIndexA;

    private int TopLeftIndexB;
    private int TopIndexB;
    private int TopRightIndexB;
    private int LeftIndexB;
    private int RightIndexB;
    private int BottomLeftIndexB;
    private int BottomIndexB;
    private int BottomRightIndexB;

    public IndexAround(){
    }

    public IndexAround(int indexA, int indexB) {
        setIndexAroundValues(indexA,indexB);
    }

    public void setIndexAroundValues(int indexA,int indexB){
        TopLeftIndexA = indexA-1;
        TopIndexA = indexA - 1;
        TopRightIndexA =indexA - 1;
        LeftIndexA =indexA;
        RightIndexA =indexA;
        BottomLeftIndexA=indexA+1;
        BottomIndexA =indexA+1;
        BottomRightIndexA =indexA+1;

        TopLeftIndexB = indexB - 1;
        TopIndexB = indexB;
        TopRightIndexB =indexB + 1;
        LeftIndexB =indexB - 1;
        RightIndexB =indexB + 1;
        BottomLeftIndexB =indexB - 1;
        BottomIndexB =indexB;
        BottomRightIndexB =indexB + 1;
    }

    public int getTopLeftIndexA() {
        return TopLeftIndexA;
    }

    public int getTopIndexA() {
        return TopIndexA;
    }

    public int getTopRightIndexA() {
        return TopRightIndexA;
    }

    public int getLeftIndexA() {
        return LeftIndexA;
    }

    public int getRightIndexA() {
        return RightIndexA;
    }

    public int getBottomLeftIndexA() {
        return BottomLeftIndexA;
    }

    public int getBottomIndexA() {
        return BottomIndexA;
    }

    public int getBottomRightIndexA() {
        return BottomRightIndexA;
    }

    public int getTopLeftIndexB() {
        return TopLeftIndexB;
    }

    public int getTopIndexB() {
        return TopIndexB;
    }

    public int getTopRightIndexB() {
        return TopRightIndexB;
    }

    public int getLeftIndexB() {
        return LeftIndexB;
    }

    public int getRightIndexB() {
        return RightIndexB;
    }

    public int getBottomLeftIndexB() {
        return BottomLeftIndexB;
    }

    public int getBottomIndexB() {
        return BottomIndexB;
    }

    public int getBottomRightIndexB() {
        return BottomRightIndexB;
    }

}
