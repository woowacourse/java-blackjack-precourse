package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.input.Input;
import domain.output.Output;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static domain.game.BlackJackGame.CARD_COUNT;

/**
 * TakingCard
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class TakingCard {
    public static List<Card> cards;
    private List<Integer> cardIndex;
    private Input input = new Input();
    private Output output = new Output();

    public void initialize() {
        cards = CardFactory.create();
        cardIndex = createCardIndex();
    }

    public List<Integer> createCardIndex() {
        List<Integer> cardIndex = new ArrayList<>();
        for (int i = 0; i < CARD_COUNT; i++) {
            cardIndex.add(i);
        }
        Collections.shuffle(cardIndex);
        return cardIndex;
    }

    public Card takeCard() {
        return cards.get(cardIndex.remove(0));
    }

    public void takeFirstCards(List<Player> players, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(takeCard());
            takeFirstPlayerCard(players);
        }
    }

    public void takeFirstPlayerCard(List<Player> players) {
        Iterator itr = players.iterator();
        while (itr.hasNext()) {
            Player player = (Player) itr.next();
            player.addCard(takeCard());
        }
    }

    public void takeMorePlayerCard(List<Player> players) {
        Iterator itr = players.iterator();
        while (itr.hasNext()) {
            Player player = (Player) itr.next();
            takeMorePlayerCard(player);
        }
    }

    public void takeMorePlayerCard(Player player) {
        while (input.askOneMoreCard(player)) {
            player.addCard(takeCard());
            output.printCards(player, false);
        }
    }

    public void takeMoreDealerCard(Dealer dealer) {
        while (dealer.isUnderDealerMinScore()) {
            dealer.addCard(takeCard());
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }
}
