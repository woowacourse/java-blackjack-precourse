package system;

import view.InputView;

import java.util.StringTokenizer;

public class BlackjackSystem {
    public void run() {
        setGame();
    }

    private void setGame() {
        String names = InputView.inputPlayerName();
        StringTokenizer st = splitPlayerName(names);
    }

    private StringTokenizer splitPlayerName(String names) {
        return new StringTokenizer(names, ",");
    }
}
