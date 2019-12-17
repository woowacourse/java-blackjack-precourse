package main;

import application.domain.card.CardSupplier;
import application.domain.user.Users;
import application.util.IndexGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssemblerTest {

    @Test
    void getBlackJackUsers() {
        Users blackJackUsers = Assembler.getUsersObject();
        System.out.println(blackJackUsers);
    }

    @Test
    void getCardTest() {
        IndexGenerator generator = () -> 3;
        CardSupplier cs = new CardSupplier(generator);
        System.out.println(cs.supply());
    }
}