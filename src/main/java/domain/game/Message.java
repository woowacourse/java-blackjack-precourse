package domain.game;

import java.lang.String;

/**
 * Message 클래스는 블랙잭 게임을 실행하면서 사용되는 시스템 출력 메세지들을 모아놓은 유틸리티 클래스이다.
 * 일단 메세지가 블랙잭 게임 내에서만 쓰여 Message라고 이름지었지만, 추후 변경될 수 있다.
 * 이 객체는 인스턴스가 생성되지 않는 것을 상정하고 설계하였다.
 *
 * 본 클래스를 사용하는 방법은 두 가지이다.
 * 1. Message.GET_NAME 이런 식으로 상수값으로 정의된 맴버들을 직접 불러오기
 * 2. message.printPlayerBetQuestion 이런 식으로 메서드를 호출해 출력하기
 *
 * @author kafka
 * @version 1.0
 */

public class Message {
    public static final String GET_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)\n";
    public static final String BET_PLAYER = "의 배팅 금액은?\n";
    public static final String FIRST_DRAW = "에게 2장의 카드를 나누었습니다.\n";
    public static final String DRAW_PLAYER_QUESTION = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    public static final String DRAW_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    public static final String PRINT_RESULT = "## 최종수익\n";
    public static final String PRINT_PLAYER_RESULT = " - 결과: ";
    public static final String ERROR_CARD_EMPTY = "더 이상 뽑을 카드가 없습니다.\n";
    public static final String ERROR_INPUT = "잘못된 값을 입력하셨습니다.\n";

    /**
     * 생성자 Message를 private으로 선언하였다.
     * 인스턴스의 생성은 고려하지 않는 객체이기 때문이다.
     * Effective java item4 의 레퍼런스를 참조하여,
     * 혹여 상속이나 잘못된 생성자 호출을 방지하기 위한 예외처리를 추가하였다.
     *
     * @exception AssertionError 생성자 호출을 비논리적 상황으로 고려하여, 선언이 실패하였다는 의미로 throw해준다.
     */
    private Message() {
        throw new AssertionError();
    }
}
