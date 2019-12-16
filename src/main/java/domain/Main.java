package domain;

public class Main {

    public static void main(String[] args) {
        PrepareGame prepareGame = new PrepareGame();
        prepareGame.prepare();

        PlayGame playGame = new PlayGame(prepareGame.getPlayers());
        playGame.play();
    }
}
