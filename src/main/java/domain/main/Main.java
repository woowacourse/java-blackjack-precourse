package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GameUser.inputTotalUsers();
        GameUser.makeUserBettingMoneyList();
        GameUser.printUserList();
    }
}
