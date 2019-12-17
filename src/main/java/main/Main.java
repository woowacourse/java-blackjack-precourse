package main;

import application.FlowController;
import application.domain.game.Game;
import application.domain.user.Users;
import application.util.IndexGenerator;
import application.util.random.RandomIndexGenerator;

public class Main {
    public static void main(String[] args) {
        Users blackJackUsers = Assembler.getUsersObject();
        IndexGenerator generator = new RandomIndexGenerator();
        Game blackJackGame = Assembler.getCardGameObject(blackJackUsers, generator);
        FlowController flow = Assembler.getGameFlow(blackJackGame, blackJackUsers);
        flow.process();
    }
}
