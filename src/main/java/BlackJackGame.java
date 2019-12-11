import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        //cards.forEach(x -> System.out.println(x.toString()));
        startGame();
    }

    private void startGame() {
        Random random = new Random();
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(CardFactory.create());
        Collections.shuffle(newCards);
        System.out.println(newCards.size());
        //players.stream().forEach(x -> x.addCard(cards.get(random.nextInt(cards.size()))));
        players.stream().forEach(x ->
                x.addCard(newCards.remove(newCards.size()-1)));
        System.out.println(newCards.size());


    }
}
