import controller.GameController;
public class App {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.initGame();
        gameController.gameStart();
        gameController.gameResult();
    }
}