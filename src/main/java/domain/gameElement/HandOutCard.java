package domain.gameElement;

import domain.card.Card;
import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Scanner;

public class HandOutCard {
    private CardDeck cardDeck = new CardDeck();
    private Scanner scanner = new Scanner(System.in);

    public void firstHandOutCards(List<User> users) {
        handOutCards((Dealer) users.get(0));
        handOutCards((Dealer) users.get(0));
        ((Dealer) users.get(0)).userCardsInfo(((Dealer) users.get(0)).getCards());
        for (int i = 1; i < users.size(); i++) {
            handOutCards((Player) users.get(i));
            handOutCards((Player) users.get(i));
            users.get(i).userCardsInfo(((Player) users.get(i)).getCards(), ((Player) users.get(i)).getName());
        }
        new UsersSumNumbers().usersSumNumbers(users);
    }

    public void proceedHandOutCards(List<User> users, int step) {
        for (int i = 1; i < users.size(); i++) {
            playerHandOutCards((Player) users.get(i));
        }
        if (users.get(0).getSumNumbers() <= 16) {
            System.out.println("딜러는 16이하라 카드 한 장을 더 받았습니다.");
            handOutCards((Dealer) users.get(0));
        }
    }

    private void playerHandOutCards(Player player) {
        while (player.getSumNumbers() < 21 && askHandOutCards(player)) {
            handOutCards(player);
            player.userCardsInfo(player.getCards(), player.getName());
            new UsersSumNumbers().usersSumNumbers(player);
        }
    }

    private boolean askHandOutCards(Player player) {
        System.out.println("\n" + player.getName() + "(은)는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String cardHandOut = "";
        do {
            cardHandOut = scanner.nextLine();
        } while (!cardHandOut.equals("y") && !cardHandOut.equals("n"));
        if (cardHandOut.equals("y")) {
            return true;
        }
        return false;
    }

    private void handOutCards(Dealer dealer) {
        Card card = handOutCards();
        dealer.addCard(card);
    }

    private void handOutCards(Player player) {
        Card card = handOutCards();
        player.addCard(card);
    }

    private Card handOutCards() {
        Card card = null;
        try {
            card = cardDeck.drawACard();
        } catch (Exception e) {
            System.out.println("카드패가 전부 소진되어 게임을 종료합니다. 사용자 수 조정을 추천합니다.");
            System.exit(0);
        }
        return card;
    }
}
