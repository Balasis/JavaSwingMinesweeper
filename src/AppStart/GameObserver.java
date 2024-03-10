package AppStart;
//The initial idea was to put observers but it avoid it since it's
//a concept a barely even heard it. I used extend instead but kept the interface...for no reason ;p
public interface GameObserver {
    void onGameOver();
    void redisplayFlagNumber();
    void checkWinCondition();
}
