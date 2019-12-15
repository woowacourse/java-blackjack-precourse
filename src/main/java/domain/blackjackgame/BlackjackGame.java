package domain.blackjackgame;

import domain.card.CardFactory;
import domain.card.Stack;
import domain.user.Users;

public class BlackjackGame {
    private Users users;
    private Stack stack;

    public BlackjackGame(Users users) {
        this.users = users;
        users.addDealer();
        this.stack = new Stack(CardFactory.create());
        stack.shuffle();
    }

    public void execute() {
        users.distribute(stack);

    }

}
