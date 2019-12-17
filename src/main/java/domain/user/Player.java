package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;


public class Player extends Gamer{
    private final String name;
    private final double bettingMoney;
    private double stat;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
        this.stat = 0;
    }
    public double getStat(){
        return stat;
    }
    public void win(){
        stat += bettingMoney;
    }
    public void draw(){
        stat = stat;
    }
    public void blackjack(){
        stat = 1.5 * bettingMoney;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Player{"+ "name=" + name + ", betting = " + bettingMoney + '}';
    }

    // TODO

}
