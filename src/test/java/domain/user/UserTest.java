package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class UserTest {

    private User user;


    @BeforeEach
    void init() {
        user = new User() {
        };
    }

    @Test
    @DisplayName("#calculateScore: Ace가 없음")
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
    @DisplayName("#calculateScore: Ace가 있고, 11점으로 계산")
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
    @DisplayName("#calculateScore: Ace가 있고, 11점으로 계산하면 버스터여서 1로 계산")
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
    @DisplayName("#isBust: 점수가 21점을 넘을 때")
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
    @DisplayName("#isBust: 점수가 21점을 넘지 않을 때")
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
    @DisplayName("#isBlackjack: 점수가 21점이고 개수가 2개일 때")
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
    @DisplayName("#isBlackjack: 점수가 21이 아닐 때")
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
    @DisplayName("#isBlackjack: 점수가 21점이기는 해도, 개수가 2개일 때.")
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