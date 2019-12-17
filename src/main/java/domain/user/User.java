package domain.user;

import domain.Game;
import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class User {
    public abstract void addCard(Card card);
    public abstract void printNameAndCards();
}
