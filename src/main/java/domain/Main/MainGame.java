package domain.Main;

import domain.Game.Game;

/**
 * @ClassName : MainGame
 * @ClassExplanation : Run Application
 * @author : Kim SeYun
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class MainGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.Play();
    }
}
