package domain.controller;

public class GameManager {
    UserCreateManager userCreateManager;

    public GameManager() {
        userCreateManager = new UserCreateManager();
    }


    public void run() {

        userCreateManager.playerStandBy();

    }
}
