package domain.user;

import domain.outcome.Outcome;
import domain.outcome.Outcomes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void decideOutcome() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new Player("pobi", 10000));
        usersList.add(new Player("ubi", 20000));
        usersList.get(0).addRandomCard();
        usersList.get(0).addRandomCard();
        usersList.get(1).addRandomCard();
        usersList.get(1).addRandomCard();
        System.out.println(usersList.get(0).calcurateScore());
        System.out.println(usersList.get(1).calcurateScore());
        Users users = new Users(usersList);
        System.out.println();
        Outcomes outcomes = new Outcomes(new ArrayList<Outcome>());
        users.decideOutcome(17, outcomes);
        System.out.println(outcomes.toString());
    }
}