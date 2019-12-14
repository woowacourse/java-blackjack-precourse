package domain.io;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Message {
    public void showNameInputMessage() {
	System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 ',' 기준으로 분리)");
    }
    
    public void showNameInputErrorMessage() {
	System.out.println("이름 입력이 잘못되었습니다. 1글자 이상의 이름을 입력해주세요.");
    }

}
