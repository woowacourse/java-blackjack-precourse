package domain;

public class BlackjackGame {

    public static void main(String[] args) {
        GameProcessor.addPlayers(InputProcessor.getPlayerName());
        GameProcessor.createDealer();
        GameProcessor.createCard();
    }
}
