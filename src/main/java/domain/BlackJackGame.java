package domain;

import domain.io.Message;
import domain.io.MoneyInput;
import domain.io.NameInput;

import domain.user.Player;
import domain.user.Dealer;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;
import java.util.ArrayList;

public class BlackJackGame {
    Message message = new Message();
    MoneyInput moneyinput = new MoneyInput();
    NameInput nameinput = new NameInput();
    private List<Card> cardList = new ArrayList<>(CardFactory.create());
    private Player[] player;
    private Dealer dealer;

    
    public void run() {}
    
    public void setPlayer() {
	String[] name = nameinput.inputName();
	for (int i = 0; i < name.length; i++) {
	    player[i] = new Player(name[i], moneyinput.inputMoney(name[i]));
	}
    }
    
    public void getCard(Dealer dealer) {
	dealer.addCard(selectCard());
    }
    
    public void getCard(Player player) {
	player.addCard(selectCard());
    }
    
    public Card selectCard(){
	int randindex=(int)(Math.random()*cardList.size());
	Card card=cardList.get(randindex);
	cardList.remove(randindex);
	return card;
    }
   
}
