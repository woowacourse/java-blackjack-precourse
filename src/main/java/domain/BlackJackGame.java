package domain;

import domain.Score;
import domain.io.*;
import domain.user.Player;
import domain.user.Dealer;
import domain.card.Card;
import domain.card.CardFactory;
import java.util.List;
import java.util.ArrayList;


/**
 * 블랙잭 게임의 전반적인 운영을 담당하는 클래스
 */
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
	if(checkFirstTurn()) {
	    return;		//게임종료
	}
	selectMoreCard();
	getDealerMoreCard();
	message.showAllCardStatus(dealer, player);
	setFinalMoney();
	message.showFinalScore(dealerMoney,playerMoney,player);
    }
    
    
    public void setPlayer() {
	dealer = new Dealer();
	String[] name = nameinput.inputName();
	playerMoney=new double[name.length];
	player=new Player[name.length];
	for (int i = 0; i < name.length; i++) {
	    this.player[i] = new Player(name[i], moneyinput.inputMoney(name[i]));
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
	message.showInitCardDistributedMessage(dealer,player);
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
	Card c=cardList.get(randindex);
	this.cardList.remove(randindex);
	return c;
    }
    
    public boolean checkFirstTurn() {
	if(checkFirstBlackJack()) {
	    message.showFinalScore(dealerMoney,playerMoney,player);
	    return true;		//게임종료
	}
	return false;
    }
    
    public void selectMoreCard() {
	for(int i=0;i<player.length;i++) {
	    getPlayerMoreCard(player[i]);
	    message.showCardStatus(player[i]);
	}
    }
    
    public void setFinalMoney() {
	if(score.getSum(dealer.getCards())>21) {
	    return;
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
	setMoneyWinnerAndLooser(winnerscore);
    }
    
    public void setMoneyWinnerAndLooser(double winnerscore) {
	for(int i=0;i<player.length;i++) {
	    if(score.getSum(player[i].getCards())!=winnerscore) {
		this.dealerMoney+=playerMoney[i];
		playerMoney[i]=0;
	    }
	}
    }
    
    public boolean checkFirstBlackJack() {
	if (!score.isBlackJack(score.getSum(dealer.getCards()))) {		//딜러가 블랙잭 아닌경우
	    return checkOnlyPlayerBlackJack();
	}
	if(checkPlayerBlackJack()) {		//딜러와 플레이어 둘 다 블랙잭인 경우 ->변경사항 없음
	   return true;
	}
	setDealerMoneyBlackJack();
	return true;
    }
    
    public void setDealerMoneyBlackJack() {
	for(int i=0;i<player.length;i++) {	//딜러만 블랙잭인경우
	this.dealerMoney+=playerMoney[i];
	playerMoney[i]=0;
	}
    }
    
    public boolean checkPlayerBlackJack() {
	for(int i=0;i<player.length;i++) {
	    if(score.isBlackJack(score.getSum(player[i].getCards()))) {
		return true;
	    }
	}
	return false;
    }
    
    /*플레이어만 블랙잭인지 확인하는 메소드*/
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
   
}
