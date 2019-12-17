package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.outcome.Outcomes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UsersTest {

    @Test
    void decideOutcome() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new Player("pobi", 10000));
        usersList.add(new Player("ubi", 20000));
        usersList.get(0).addFixCard(new Card(Symbol.TWO, Type.클로버));
        usersList.get(1).addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        usersList.get(1).addFixCard(new Card(Symbol.ACE, Type.스페이드));
        usersList.get(1).addFixCard(new Card(Symbol.ACE, Type.클로버));
        System.out.println(usersList.get(0).calcurateScore());
        System.out.println(usersList.get(1).calcurateScore());
        Users users = new Users(usersList);
        System.out.println();
        Outcomes outcomes = new Outcomes();
        users.decideOutcome(24, outcomes);
        outcomes.calcurateDealerBenefit();
        System.out.println(outcomes.toString());
    }

    @Test
    void decideOutcome1() {
    }
}