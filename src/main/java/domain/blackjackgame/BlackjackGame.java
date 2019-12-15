package domain.blackjackgame;

import domain.card.CardFactory;
import domain.card.Stack;
import domain.user.Users;

public class BlackjackGame {
    private Users users;
    private Stack stack;

    public BlackjackGame(Users users) {
        this.users = users;
        this.stack = new Stack(CardFactory.create());
        stack.shuffle();
    }

    public void execute() {
        Distribute();

    }

    private void Distribute() {
        users.distribute(stack);
        //users.print
    }

}
