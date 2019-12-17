package domain;

import domain.Score;
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
    private double[] playerMoney;
    private double dealerMoney = 0;

    
    public void run() {
  	setPlayer();
	getCardFirst();
	message.showInitCardDistributedMessage(dealer,player);
	if(checkBlackJack()) {
	    message.showFinalScore(dealerMoney,playerMoney,player);
	    return;		//게임종료
	}
	for(int i=0;i<player.length;i++) {
	    getPlayerMoreCard(player[i]);
	    message.showCardStatus(player[i]);
	}
	getDealerMoreCard();
	message.showAllCardStatus(dealer, player);
	setFinalMoney();
	message.showFinalScore(dealerMoney,playerMoney,player);
    }
    
    public void setFinalMoney() {
	if(score.getSum(dealer.getCards())>21) {
	}
	findWinner();
    }
    
    public void findWinner() {
	double playerscore;
	double winnerscore=score.getSum(dealer.getCards());
	for(int i=0;i<player.length;i++) {
	    playerscore=score.getSum(player[i].getCards());
	    if(playerscore<21 &&playerscore>=winnerscore ) {
		winnerscore=playerscore;
	    }
	}
	for(int i=0;i<player.length;i++) {
	    if(score.getSum(player[i].getCards())!=winnerscore) {
		this.dealerMoney+=playerMoney[i];
		playerMoney[i]=0;
	    }
	}
    }
    
    public boolean checkBlackJack() {
	boolean deal=score.isBlackJack(score.getSum(dealer.getCards()));
	if (!deal) {		//딜러가 블랙잭 아닌경우
	    return checkOnlyPlayerBlackJack();
	}
	/*딜러가 블랙잭인경우*/
	if(checkPlayerBlackJack()) {	//둘 다 블랙잭이면 변경사항 없음
	   return true;
	}
	for(int i=0;i<player.length;i++) {	//딜러만 블랙잭인경우
	this.dealerMoney+=playerMoney[i];
	playerMoney[i]=0;
	}
	return true;
    }
    
    public boolean checkPlayerBlackJack() {
	for(int i=0;i<player.length;i++) {
	    if(score.isBlackJack(score.getSum(player[i].getCards()))) {
		return true;
	    }
	}
	return false;
    }
    
    public boolean checkOnlyPlayerBlackJack() {
	boolean check=false;
	for(int i=0;i<player.length;i++) {
	    if(score.isBlackJack(score.getSum(player[i].getCards()))) {
		check=true;
		playerMoney[i]=player[i].getBettingMoney()*1.5;
		this.dealerMoney+=playerMoney[i]-player[i].getBettingMoney();
	    }
	}
	return check;
    }
    
    public void setPlayer() {
	String[] name = nameinput.inputName();
	playerMoney=new double[name.length];
	for (int i = 0; i < name.length; i++) {
	    player[i] = new Player(name[i], moneyinput.inputMoney(name[i]));
	    playerMoney[i]=player[i].getBettingMoney();
	}
    }
    
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
