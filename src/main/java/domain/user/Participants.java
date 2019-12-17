package domain.user;

import domain.BlackJackGame;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static domain.card.Symbol.ACE;

public class Participants {

    private final List<Card> cards = new ArrayList<>();
    private int total = 0;
    public int revenue = 0;



    public List<Card> getCards() {
        return this.cards;
    }




}
