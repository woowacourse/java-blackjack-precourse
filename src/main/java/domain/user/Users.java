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
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.printCards();
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

    public void processEachUser(Stack stack) {
        for (User user : users) {
            processPlayer(user, stack);
            processDealer(user, stack);
        }
    }

    private void processDealer(User user, Stack stack) {
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.proceed(stack);
        }
    }

    private void processPlayer(User user, Stack stack) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.proceed(stack);
        }
    }

    public void showResult() {
        for (User user : users) {
            showDealerResult(user);
            showPlayerResult(user);
        }
    }

    private void showDealerResult(User user) {
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.showResult();
        }
    }

    private void showPlayerResult(User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.showResult();
        }
    }

    public void showProfits() {    //TODO  중복되는 코드 제거하기. Dealer와 List<Player>를 리턴함으로써 가능할 듯
        getDealer();
    }

    /* Dealer getDealer() {
        Dealer dealer = new Dealer();
        for (User user : users) {
            dealer = checkDealer(user, dealer);
            if (user instanceof Dealer) {
                dealer = (Dealer) user;
            }
        }
        return dealer;
    }

    private Dealer checkDealer(User user) {
        try {
            Dealer dealer = (Dealer) user;
            return dealer;
        } catch (Exception e) {

        }
        return null;
    }*/


}
