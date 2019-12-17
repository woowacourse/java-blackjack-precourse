package util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.BlackjackConfig;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

class BlackjackPrinterImplTest {

    private BlackjackPrinterImpl blackjackPrinter;
    private final ByteArrayOutputStream outView = new ByteArrayOutputStream();


    @BeforeEach
    void init() {
        blackjackPrinter = new BlackjackPrinterImpl();
        System.setOut(new PrintStream(outView));
    }

    @Test
    void printUserStateWhenPlayer() {
        Player player = new Player("pobi", 100);
        List<Card> cards = makeCards();
        for (Card card : cards) {
            player.addCard(card);
        }

        blackjackPrinter.printUserState(player);

        assertEquals("pobi카드: 3다이아몬드, 9클로버, 8스페이드\n", outView.toString());
    }

    @Test
    void printUserStateWhenDealer() {
        //given
        User dealer = new Dealer();
        List<Card> cards = makeCards();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        //when
        blackjackPrinter.printUserState(dealer);
        //then
        assertEquals("딜러카드: 3다이아몬드\n", outView.toString());
    }

    @Test
    void printUserResult() {
        //given
        Dealer dealer = new Dealer();
        List<Card> cards = makeCards();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        //when
        blackjackPrinter.printUserResult(dealer);
        //then
        assertEquals("딜러카드: 3다이아몬드, 9클로버, 8스페이드 - 결과: 20\n", outView.toString());
    }

    private List<Card> makeCards() {
        return Arrays.asList(
                new Card(Symbol.THREE, Type.DIAMOND),
                new Card(Symbol.NINE, Type.CLUB),
                new Card(Symbol.EIGHT, Type.SPADE)
        );
    }

    @Test
    void printDealerHit() {
        //given
        Dealer dealer = new Dealer();
        //when
        blackjackPrinter.printDealerHit(dealer);
        //then
        assertEquals(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.\n",
                dealer, BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER), outView.toString());
    }
}