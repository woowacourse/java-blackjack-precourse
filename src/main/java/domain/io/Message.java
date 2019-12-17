package domain.io;

import domain.user.Dealer;
import domain.user.Player;

/**
 * 프로그램 이용자가 보는 view message 부분을 담당하는 클래스
 */
public class Message {
    public void showNameInputMessage() {
	System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 ',' 기준으로 분리)");
    }
    
    public void showNameInputErrorMessage() {
	System.out.println("이름 입력이 잘못되었습니다. 1글자 이상의 이름을 입력해주세요.");
    }
    
    public void showMoneyInputMessage(String name) {
	System.out.println(name+"의 배팅 금액은?");
    }
    
    public void showMoneyInputErrorMessage() {
	System.out.println("금액 입력이 잘못되었습니다. 1 이상의 숫자만 입력해주세요.");
    }   
    
    public void showInitCardDistributedMessage(Dealer dealer,Player[] player) {
	String[] name = new String[player.length];
	for(int i=0;i<name.length;i++) {
	    name[i]=player[i].getName();
	}
	System.out.println("딜러와 "+" "+String.join(",", name)+"에게 2장의 카드를 나눠줬습니다.");
	showAllCardStatus(dealer,player);
    }
    
    public void showAllCardStatus(Dealer dealer, Player[] player) {
	showCardStatus(dealer);
	for(int i=0;i<player.length;i++) {
	    showCardStatus(player[i]);
	}
	System.out.println();
    }
    
    public void showCardStatus(Player player) {
	System.out.println(player.getName()+" 카드 : "+String.join(",",player.getCards().toString()));
    }
    
    public void showCardStatus(Dealer dealer) {
	System.out.println("딜러 카드 : "+String.join(",",dealer.getCards().toString()));
    }
    
    public void showGettingCardQuestion(String name) {
	System.out.println(name+"은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n 입력)");
    }
    
    public void showGettingCardErrorMessage() {
	System.out.println("입력이 잘못되었습니다. y(Y)혹은 n(N)만 입력해주세요");
    }
    
    public void showDealerMoreCard() {
	System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.\n");
    }
    
    public void showDealerNotMoreCard() {
	System.out.println("딜러는 16초과라 카드를 더 받지 않았습니다.\n");
    }
    
    public void showFinalScore(double d, double[] p,Player[] player) {
	System.out.println("## 최종 수익");
	System.out.println("딜러 : "+d);
	for(int i=0;i<player.length;i++) {
	    System.out.println(player[i].getName()+ " : "+p[i]);
	}
    }
}
