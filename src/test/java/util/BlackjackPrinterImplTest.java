package util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;

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
//        System.setOut(new PrintStream(outView));
    }

    @Test
    void printUserStateWhenPlayer() {
        //todo: check rafactoring
        User player = new Player("pobi", 100);
        Card spade8 = new Card(Symbol.EIGHT, Type.SPADE);
        Card heartA = new Card(Symbol.ACE, Type.HEART);
        player.addCard(spade8);
        player.addCard(heartA);

        blackjackPrinter.printUserState(player);

        assertEquals("pobi카드: 8스페이드, A하트\n", outView.toString());
    }

    @Test
    void printUserStateWhenDealer() {
        //todo: check rafactoring
        User dealer = new Dealer();
        Card diamond3 = new Card(Symbol.THREE, Type.DIAMOND);
        Card club9 = new Card(Symbol.NINE, Type.CLUB);
        Card spade8 = new Card(Symbol.EIGHT, Type.SPADE);
        dealer.addCard(diamond3);
        dealer.addCard(club9);
        dealer.addCard(spade8);

        blackjackPrinter.printUserState(dealer);

        assertEquals("딜러카드: 3다이아몬드\n", outView.toString());
    }

    @Test
    void printUserResult() {
        //todo: check rafactoring
        User dealer = new Dealer();
        Card diamond3 = new Card(Symbol.THREE, Type.DIAMOND);
        Card club9 = new Card(Symbol.NINE, Type.CLUB);
        Card spade8 = new Card(Symbol.EIGHT, Type.SPADE);
        dealer.addCard(diamond3);
        dealer.addCard(club9);
        dealer.addCard(spade8);

        blackjackPrinter.printUserResult(dealer);
    }

    @Test
    void printDealerHit() {
        Dealer dealer = new Dealer();
        blackjackPrinter.printDealerHit(dealer);

        //todo: check convention
        assertEquals(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.\n", dealer, BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER), outView.toString());
    }
}