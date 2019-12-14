package domain.controller;

public class GameManager {
    PlayerCreateManager playerCreateManager;

    public GameManager() {
        playerCreateManager = new PlayerCreateManager();
    }


    public void run() {

        playerCreateManager.playerStandBy();

    }
}
