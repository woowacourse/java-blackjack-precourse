package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class UserTest {

    private User user;


    @BeforeEach
    void init() {
        user = new User() {};
    }

    @Test
    void calculateScoreWithoutAce() {
        //given
        List<Card> cardsWithoutAce = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB)
        );
        for (Card card : cardsWithoutAce) {
            user.addCard(card);
        }

        //when & then
        assertEquals(8 + 10, user.calculateScore());
    }

    @Test
    void calculateScoreWithAceWhenNotExceedsBlackjack() {
        //given
        List<Card> cardsWithAce = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.ACE, Type.CLUB)
        );
        for (Card card : cardsWithAce) {
            user.addCard(card);
        }

        //when & then
        assertEquals(8 + 11, user.calculateScore());
    }

    @Test
    void calculateScoreWithAceWhenExceedsBlackjack() {
        //given
        List<Card> cardsWithAce = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.ACE, Type.CLUB)
        );
        for (Card card : cardsWithAce) {
            user.addCard(card);
        }

        //when & then
        assertEquals(8 + 10 + 1, user.calculateScore());
    }

    @Test
    void isBustWhenBust() {
        //given
        List<Card> cardsWhichBust = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB)
        );
        for (Card card : cardsWhichBust) {
            user.addCard(card);
        }

        //when & then
        assertTrue(user.isBust());
    }

    @Test
    void isBustWhenNotBust() {
        //given
        List<Card> cardsWhichNotBust = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB)
        );
        for (Card card : cardsWhichNotBust) {
            user.addCard(card);
        }
        //when & then
        assertFalse(user.isBust());
    }

    @Test
    void isBlackjackWhenBlackjack() {
        //given
        List<Card> blackjack = Arrays.asList(
                new Card(Symbol.ACE, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB)
        );
        for (Card card : blackjack) {
            user.addCard(card);
        }
        //when & then
        assertTrue(user.isBlackjack());
    }

    @Test
    void isBlackjackWhenNotBlackjackScore() {
        //given
        List<Card> notBlackjack = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB)
        );
        for (Card card : notBlackjack) {
            user.addCard(card);
        }
        //when & then
        assertFalse(user.isBlackjack());
    }

    @Test
    void isBlackjackWhenNotBlackjackSize() {
        //given
        List<Card> notBlackjack = Arrays.asList(
                new Card(Symbol.EIGHT, Type.SPADE),
                new Card(Symbol.KING, Type.CLUB),
                new Card(Symbol.THREE, Type.SPADE)
        );
        for (Card card : notBlackjack) {
            user.addCard(card);
        }
        //when & then
        assertEquals(21, user.calculateScore());
        assertFalse(user.isBlackjack());
    }
}