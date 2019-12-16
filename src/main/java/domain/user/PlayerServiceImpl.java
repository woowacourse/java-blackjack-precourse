package domain.user;

import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.CardConfig;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl extends UserService {
    private UserInterface userInterface;
    private UserFactory userFactory;

    public PlayerServiceImpl(Deck deck, BlackjackPrinter blackjackPrinter, UserInterface userInterface, UserFactory userFactory) {
        super(deck, blackjackPrinter);
        this.userInterface = userInterface;
        this.userFactory = userFactory;
    }

    public void confirmCards(List<Player> players) {
        for (Player player : players) {
            confirmCards(player);
        }
    }

    @Override
    public void confirmCards(User user) {
        if (isBust(user)) {
            //todo: refac
            System.out.println(String.format("%s는 버스트하여, 더이상 카드를 받을 수 없습니다.", user));
            return;
        }

        blackjackPrinter.printRequestForHit(user);
        String will = userInterface.getWillForMoreCard();
        if (will.equals(Will.Stay.getValue())) {
             blackjackPrinter.printUserState(user);
             return;
        }

        hit(user);
    }

    private void hit(User user) {
        user.addCard(deck.pick());
        blackjackPrinter.printUserState(user);
        confirmCards(user);
    }

    private boolean isBust(User user) {
        int score = user.calculateScore();
        return CardConfig.BLACKJACK < score;
    }

    public List<Player> join() {
        List<Player> players = new ArrayList<>();
        blackjackPrinter.printRequestForNames();
        String[] names = userInterface.extractNames();
        //todo: refac
        System.out.println();
        for (String name : names) {
            blackjackPrinter.printRequestForMoney(name);
            int bettingMoney = userInterface.getBettingMoney();
            //todo: refac
            System.out.println();
            players.add(userFactory.create(name, bettingMoney));
        }
        return players;
    }

    public void receiveDefaultCards(List<Player> players) {
        for (Player player : players) {
            receiveDefaultCards(player);
        }
    }

    public void printResult(List<Player> players) {
        for (Player player : players) {
            blackjackPrinter.printUserResult(player);
        }
    }
}
