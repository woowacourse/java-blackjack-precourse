package domain;

import domain.Score;
import domain.Money;
import domain.io.*;
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
    MoreCardInput morecardinput = new MoreCardInput();
    Score score = new Score();
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
    
    //
    public void getCardFirst() {
	for(int i=0;i<2;i++) {
	    getCard(dealer);
	}
	for(int i=0;i<player.length;i++) {
	    getCard(player[i]);
	    getCard(player[i]);
	}
    }
    
    public void getPlayerMoreCard(Player player) {
	while(score.getSum(player.getCards())<=21&&morecardinput.getMoreCardOrNot(player.getName())){
	    getCard(player);
	    message.showCardStatus(player);
	}
    }
    
    public void getDealerMoreCard() {
	if(score.isLessthanSeventeen(dealer.getCards())) {
	    getCard(dealer);
	    message.showDealerMoreCard();
	    return;
	}
	message.showDealerNotMoreCard();
	return;
    }
    //
    
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
