public class FlagsCounter {
 private static int flagsCounter=9;



    public static void increaseFlagsCounter() {
        FlagsCounter.flagsCounter++;
    }
    public static void decreaseFlagsCounter() {
        FlagsCounter.flagsCounter--;
    }

    public static int getFlagsCounter() {
        return flagsCounter;
    }
}
