package domain.gameElement;

import domain.card.Card;
import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;

public class HandOutCard {
    private CardDeck cardDeck = new CardDeck();

    public void handOutCards(Dealer dealer) {
        Card card = handOutCards();
        dealer.addCard(card);
    }

    public void handOutCards(Player player) {
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
