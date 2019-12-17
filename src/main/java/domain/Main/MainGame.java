package domain.Main;

import domain.Game.Game;

/**
 * @author : Kim SeYun
 * @ClassName : MainGame
 * @ClassExplanation : Run Application
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class MainGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.Play();
    }
}
