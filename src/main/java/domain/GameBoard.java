package domain;

import domain.card.CardFactory;
import domain.card.Card;

import domain.user.Gamer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GameBoard {
    List<Card> cards = CardFactory.create();
    List<Integer> cardIndex = new ArrayList<>();            //카드에 해당하는 인덱스
    List<Gamer> gamer = new ArrayList<>();
    int head = 0;                           //맨 위 카드


    public GameBoard(String[] player, List<Integer> bettingMoney) {
        gamer.add(new Dealer());

        for (int i = 0; i < player.length; i++) {
            gamer.add(createPlayer(player[i], bettingMoney.get(i)));
        }

        shuffleCards();
        init();
    }

    public Player createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);

        return player;
    }

    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            cardIndex.add(i);
        }

        Collections.shuffle(cardIndex);
    }

    public void init() {
        System.out.print("딜러와 ");
        for (int i = 1 ; i < gamer.size(); i++) {
            Player player = (Player)gamer.get(i);
            System.out.print(player.getName());
        if (i != (gamer.size() - 1)) {
            System.out.print(",");
            }
        }
        System.out.println("에게 2 장을 나누어 주었습니다.");

        for (Gamer g : gamer) {
            g.addCard(cards.get(cardIndex.get(head++)));
            g.addCard(cards.get(cardIndex.get(head++)));
            printCards(g);
        }
    }

    public void printCards(Gamer gamer) {
        if (gamer.getClass() == Dealer.class) {
            System.out.print("딜러 : ");

            for (Card c : gamer.getCards()){
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
            }
            System.out.println("");

        }

        if (gamer.getClass() == Player.class) {
            Player player = (Player) gamer;
            System.out.print(player.getName() + " : ");

            for (Card c : gamer.getCards())
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
            System.out.println("");
        }

    }




}
