package domain.user;

import domain.card.Stack;

import java.util.List;

public class Users {
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;

    }

    public void distribute(Stack stack) {
        for (User user : users) {
            addMultipleCards(stack, user, 2);
        }
    }

    private void addMultipleCards(Stack stack, User user, int number) {
        for (int i = 0; i < number; i++) {
            user.addCard(stack.popCard());
        }
    }
}
