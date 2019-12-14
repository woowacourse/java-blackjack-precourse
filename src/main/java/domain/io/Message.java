package domain.io;

import domain.card.Card;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    public void showInitCardDistributedMessage(Player[] player) {
	String[] name = new String[player.length];
	System.out.print("딜러와 "+" "+String.join(",", name)+"에게 2장의 카드를 나눠줬습니다.");
    }
    
    public void showCardStatus(String name,List<Card> card) {
	System.out.println(name+" 카드 : "+String.join(",",card.toString()));
    }
    
    public void showGettingCardQuestion(String name) {
	System.out.println(name+"은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n 입력)");
    }
    
    public void showGettingCardErrorMessage() {
	System.out.println("입력이 잘못되었습니다. y(Y)혹은 n(N)만 입력해주세요");
    }
}
