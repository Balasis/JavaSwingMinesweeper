package AppStart;

public class FlagsCounter {
 private int flagsCounter=9;

    public void increaseFlagsCounter() {
        flagsCounter++;
    }
    public void decreaseFlagsCounter() {
        flagsCounter--;
    }

    public int getFlagsCounter() {
        return flagsCounter;
    }
}
