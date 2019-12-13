package domain;

import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(CardFactory.create());

        List<Player> players = dealer.makePlayers();
        if (!dealer.checkIfBlackJackInFirstDraws(players, cardDeck)) {    // 첫 패에 블랙잭이 있다면 종료
            dealer.haveMoreCards(players, cardDeck);
        }
    }
}
