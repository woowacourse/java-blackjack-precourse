package domain.user;

public enum Result {
    PlayerWin(1),
    PlayerLose(2),
    Draw(3),
    PlayerWinWithBlackjack(4);

    private int value;
    Result(int value) {
        this.value = value;
    }
}
