package domain.construtor;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Random;

public class CardDrawing {
    static List<Card> cards = CardFactory.create();
    Random randomIndex = new Random();

    public void startShuffle(List<Player> playerList, Dealer dealer){
        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));
        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));
        dealer.addCard(cards.get(randomIndex.nextInt(52)));
        dealer.addCard(cards.get(randomIndex.nextInt(52)));
    }
}
