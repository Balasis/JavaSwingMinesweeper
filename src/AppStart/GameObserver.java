package AppStart;
//The initial idea was to put observers I avoid it since it's
//a concept I barely know. I used extend instead but kept the interface...for no reason ;p
public interface GameObserver {
    void onGameOver();
    void redisplayFlagNumber();
    void checkWinCondition();
}
