package api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.*;
import domain.user.*;

class BlackjackApiTest {

    private BlackjackApi blackjackApi;
    private BlackjackPrinter mockBlackjackPrinter = mock(BlackjackPrinter.class);
    private UserInterface mockUserInterface = mock(UserInterface.class);
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void init() {
        Deck deck = new SingleDeck();
        PlayerFactory playerFactory = new PlayerFactory();
        DealerService dealerService = new DealerServiceImpl(deck, mockBlackjackPrinter);
        PlayerService playerService = new PlayerServiceImpl(deck, mockBlackjackPrinter, mockUserInterface, playerFactory);
        blackjackApi = new BlackjackApi(dealerService, playerService, mockBlackjackPrinter);
    }

    @Test
    void matchWhenDealerBustPlayerBust() {
        List<Card> cardsWhichBust = makeCardsWhichBust();
        double bettingMoney = 100;
        dealer = makeDealer(cardsWhichBust);
        player = makePlayer(cardsWhichBust);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    void matchWhenDealerBustPlayerNotBustNotBlackjack() {
        double bettingMoney = 100;
        List<Card> cardsWhichBust = makeCardsWhichBust();
        dealer = makeDealer(cardsWhichBust);
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        player = makePlayer(cardsWhichNotBust);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney, dealer.getProfit());
        assertEquals(bettingMoney, player.getProfit());
    }

    @Test
    void matchWhenDealerBustPlayerNotBustAndBlackjack() {
        List<Card> cardsWhichBust = makeCardsWhichBust();
        dealer = makeDealer(cardsWhichBust);
        double bettingMoney = 100;
        List<Card> blackjack = makeBlackjack();
        player = makePlayer(blackjack);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney * BlackjackConfig.BLACKJACK_RATE, dealer.getProfit());
        assertEquals(bettingMoney * BlackjackConfig.BLACKJACK_RATE, player.getProfit());
    }

    @Test
    void matchWhenDealerNotBustPlayerBust() {
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        dealer = makeDealer(cardsWhichNotBust);
        double bettingMoney = 100;
        List<Card> cardsWhichBust = makeCardsWhichBust();
        player = makePlayer(cardsWhichBust);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    void matchWhenDraw() {
        //given
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        dealer = makeDealer(cardsWhichNotBust);
        player = makePlayer(cardsWhichNotBust);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(0, dealer.getProfit());
        assertEquals(0, player.getProfit());
    }

    @Test
    void matchWhenDelaerWinWithoutBustAndBlackjack() {
        List<Card> cardsBigger = makeCardsWhichNotBust();
        dealer = makeDealer(cardsBigger);
        double bettingMoney = 100;
        List<Card> cardsSmaller = makeCardsSmaller();
        player = makePlayer(cardsSmaller);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    void matchWhenPlayerWinWithoutBustAndBlackjack() {
        List<Card> cardsSmaller = makeCardsSmaller();
        dealer = makeDealer(cardsSmaller);
        double bettingMoney = 100;
        List<Card> cardsBigger = makeCardsWhichNotBust();
        player = makePlayer(cardsBigger);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney, dealer.getProfit());
        assertEquals(bettingMoney, player.getProfit());
    }

    @Test
    void matchWhenPlayerWinWithoutBustWithBlackjack() {
        List<Card> cardsSmaller = makeCardsSmaller();
        dealer = makeDealer(cardsSmaller);
        double bettingMoney = 100;
        List<Card> blackjack = makeBlackjack();
        player = makePlayer(blackjack);
        //when
        blackjackApi.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney * BlackjackConfig.BLACKJACK_RATE, dealer.getProfit());
        assertEquals(bettingMoney * BlackjackConfig.BLACKJACK_RATE, player.getProfit());
    }

    private List<Card> makeCardsWhichBust() {
        return Arrays.asList(
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.QUEEN, Type.SPADE),
                new Card(Symbol.JACK, Type.SPADE)
        );
    }

    private List<Card> makeCardsWhichNotBust() {
        return Arrays.asList(
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.QUEEN, Type.SPADE)
        );
    }

    private List<Card> makeCardsSmaller() {
        return Collections.singletonList(
                new Card(Symbol.KING, Type.SPADE)
        );
    }

    private List<Card> makeBlackjack() {
        return Arrays.asList(
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.ACE, Type.SPADE)
        );
    }

    private Dealer makeDealer(List<Card> cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }

    private Player makePlayer(List<Card> cards) {
        double bettingMoney = 100;
        Player player = new Player("pobi", bettingMoney);
        for (Card card : cards) {
            player.addCard(card);
        }
        return player;
    }
}