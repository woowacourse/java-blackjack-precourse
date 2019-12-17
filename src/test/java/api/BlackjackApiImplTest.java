package api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.*;
import domain.user.*;

class BlackjackApiImplTest {

    private BlackjackApiImpl blackjackApiImpl;
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
        blackjackApiImpl = new BlackjackApiImpl(dealerService, playerService, mockBlackjackPrinter);
    }

    @Test
    @DisplayName("#match: 딜러, 플레이어 모두 버스트하여 딜러가 이길 때")
    void matchWhenDealerBustPlayerBust() {
        List<Card> cardsWhichBust = makeCardsWhichBust();
        double bettingMoney = 100;
        dealer = makeDealer(cardsWhichBust);
        player = makePlayer(cardsWhichBust);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    @DisplayName("#match: 딜러가 버스트하여 플레이어가 블랙잭 없이 이겼을 때")
    void matchWhenDealerBustPlayerNotBustNotBlackjack() {
        double bettingMoney = 100;
        List<Card> cardsWhichBust = makeCardsWhichBust();
        dealer = makeDealer(cardsWhichBust);
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        player = makePlayer(cardsWhichNotBust);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney, dealer.getProfit());
        assertEquals(bettingMoney, player.getProfit());
    }

    @Test
    @DisplayName("#match: 딜러가 버스트하고, 플레이어는 블랙잭으로 이겼을 때")
    void matchWhenDealerBustPlayerNotBustAndBlackjack() {
        List<Card> cardsWhichBust = makeCardsWhichBust();
        dealer = makeDealer(cardsWhichBust);
        double bettingMoney = 100;
        List<Card> blackjack = makeBlackjack();
        player = makePlayer(blackjack);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney * BlackjackConfig.BLACKJACK_RATE, dealer.getProfit());
        assertEquals(bettingMoney * BlackjackConfig.BLACKJACK_RATE, player.getProfit());
    }

    @Test
    @DisplayName("#match: 플레이어가 버스트하여 졌을 때(딜러는 버스트 안함.)")
    void matchWhenDealerNotBustPlayerBust() {
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        dealer = makeDealer(cardsWhichNotBust);
        double bettingMoney = 100;
        List<Card> cardsWhichBust = makeCardsWhichBust();
        player = makePlayer(cardsWhichBust);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    @DisplayName("#match: 플레이어가 딜러와 비겼을 때")
    void matchWhenDraw() {
        //given
        List<Card> cardsWhichNotBust = makeCardsWhichNotBust();
        dealer = makeDealer(cardsWhichNotBust);
        player = makePlayer(cardsWhichNotBust);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(0, dealer.getProfit());
        assertEquals(0, player.getProfit());
    }

    @Test
    @DisplayName("#match: 플레이어가 버스트, 블랙잭 없이 졌을 때")
    void matchWhenDelaerWinWithoutBustAndBlackjack() {
        List<Card> cardsBigger = makeCardsWhichNotBust();
        dealer = makeDealer(cardsBigger);
        double bettingMoney = 100;
        List<Card> cardsSmaller = makeCardsSmaller();
        player = makePlayer(cardsSmaller);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(bettingMoney, dealer.getProfit());
        assertEquals(-bettingMoney, player.getProfit());
    }

    @Test
    @DisplayName("#match: 플레이어가 블랙잭 없이 이겼을 때")
    void matchWhenPlayerWinWithoutBustAndBlackjack() {
        List<Card> cardsSmaller = makeCardsSmaller();
        dealer = makeDealer(cardsSmaller);
        double bettingMoney = 100;
        List<Card> cardsBigger = makeCardsWhichNotBust();
        player = makePlayer(cardsBigger);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
        //then
        assertEquals(-bettingMoney, dealer.getProfit());
        assertEquals(bettingMoney, player.getProfit());
    }

    @Test
    @DisplayName("#match: 플레이어가 블랙잭으로 이겼을 때")
    void matchWhenPlayerWinWithoutBustWithBlackjack() {
        List<Card> cardsSmaller = makeCardsSmaller();
        dealer = makeDealer(cardsSmaller);
        double bettingMoney = 100;
        List<Card> blackjack = makeBlackjack();
        player = makePlayer(blackjack);
        //when
        blackjackApiImpl.match(dealer, Collections.singletonList(player));
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