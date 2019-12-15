package domain.user;

import domain.card.Stack;

import java.util.ArrayList;
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
        printDistribution();
    }

    private void printDistribution() {
        ArrayList<String> playerNames = new ArrayList<>();

        for (User user : users) {
            getNameOfPlayer(playerNames, user);
        }
        String playerNameSerial = String.join(", ", playerNames);
        System.out.println("딜러와 " + playerNameSerial + "에게 2장을 나누었습니다");
        printOpen();
    }

    private void printOpen() {
        for (User user : users) {
            printOpenEachUser(user);
        }
    }

    private void printOpenEachUser(User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.printCards();
        }
    }

    private void getNameOfPlayer(ArrayList<String> playerNames, User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            playerNames.add(player.getName());
        }
    }

    private void addMultipleCards(Stack stack, User user, int number) {
        for (int i = 0; i < number; i++) {
            user.addCard(stack.popCard());
        }
    }

    public void addDealer() {
        users.add(new Dealer());
    }
}
