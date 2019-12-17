package domain.user;

import domain.BlackJackGame;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static domain.card.Symbol.ACE;

public class Participants {

    private final List<Card> cards = new ArrayList<>();
    private int total = 0;
    public int revenue = 0;

    public void addCard(Card card) {
        cards.add(card);
        addTotal(card);

    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void cardDistribution() {
        Random random = new Random();
        int pickedNumber = random.nextInt(52);
        if (BlackJackGame.isTaken[pickedNumber] == false) {
            this.addCard(BlackJackGame.cardList.get(pickedNumber));
            BlackJackGame.isTaken[pickedNumber] = true;
            return;
        }
        cardDistribution();
    }

    public String cardListing() {
        String cardList = this.cards.get(0).toString();
        for (int i = 1; i < cards.size(); i++) {
            cardList += ", " + this.cards.get(i).toString();
        }
        return cardList;
    }

    public void addTotal(Card card) {
        total += card.getSymbol().getScore();
    }

    public int getTotal() {
        return this.total;
    }

    public boolean hasAce() {
        for (Card card : cards) {
            if (card.getSymbol() == ACE) {
                return true;
            }
        }
        return false;
    }

    public boolean isFirstBlackJack() {
        if (this.getTotal() == 11 && hasAce() == true) {
            return true;
        }
        return false;
    }

    public int hasBeatDealer() {
        if (BlackJackGame.dealer.getTotal() > 21) {
            return 1;
        }
        if (BlackJackGame.dealer.getTotal() < 21 && this.getTotal() < 21 && (BlackJackGame.dealer.getTotal() < this.getTotal())) {
            return 1;
        }
        if (BlackJackGame.dealer.getTotal() == this.getTotal()) {
            return 0;
        }
        return -1;
    }


}
