package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameProcessor {
    private static ArrayList<Player> playersArray = new ArrayList<Player>();

    static ArrayList<Player> addPlayers(ArrayList<String> playerNamesArray) {
        for (String playerName : playerNamesArray) {
            Double playerMoney = InputProcessor.getPlayerMoney(playerName);
            playersArray.add(new Player(playerName, playerMoney));
        }
        //for (int i = 0; i < playersArray.size(); i++) {
        //    System.out.println(playersArray.get(i).getName() + ", " + (int)playersArray.get(i).getBettingMoney());
        //}
        return playersArray;
    }

    static Dealer createDealer() {
        Dealer dealer = new Dealer();
        return dealer;
    }

    static List<Card> createCard() {
        CardFactory cardFactory = new CardFactory();
        List<Card> cardsArray = new ArrayList(cardFactory.create());
        Collections.shuffle(cardsArray);
        return cardsArray;
    }

    static Card dealCard(List<Card> cardsArray) {
        Card dealtCard = cardsArray.get(0);
        cardsArray.remove(0);
        return dealtCard;
    }
}