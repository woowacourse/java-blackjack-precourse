package domain.tools;

import domain.card.Card;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolBox {
    private static final String COMMA = ",";
    private static final String SLASH = " / ";


    public List<String> splitPlayerName(String playerName) {
        return Arrays.asList(playerName.split(","));
    }


    public String joinString(Player[] players) {
        return String.join(COMMA, makeNameList(players));
    }

    public String joinString(List<Card> cards) {
        return String.join(SLASH, makeNameList(cards));
    }

    public List<String> makeNameList(Player[] players) {
        List<String> playerNameList = new ArrayList<>();
        for (Player player : players) {
            playerNameList.add(player.getName());
        }
        return playerNameList;
    }

    public List<String> makeNameList(List<Card> cards) {
        List<String> cardList = new ArrayList<>();
        for (Card card : cards) {
            cardList.add(card.toString());
        }
        return cardList;
    }

}
