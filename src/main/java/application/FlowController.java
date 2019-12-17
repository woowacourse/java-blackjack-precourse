package application;

import application.domain.game.Game;
import application.domain.game.Result;

public class FlowController {
    private final Game game;
    private final Result result;

    public FlowController(Game game, Result result) {
        this.game = game;
        this.result = result;
    }

    public void process() {
        game.play();
        result.calculateAndPrintResult();
    }
}
