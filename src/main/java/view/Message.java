/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Message.java
 * 입출력 메세지들을 담고 있는 enum
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package view;

public enum Message {
    INPUT_PLAYER_NAME("게임에 참여할 사람의 이름을 입력하세요(쉼표 기준으로 분리)"),
    ERROR_BLANK_NAME("이름은 한 자 이상의 문자로 이루어져야합니다."),
    ERROR_PLAYER_NOT_EXIST("플레이어는 1명 이상 존재해야 합니다."),
    ERROR_NAME_IS_DEALER("플레이어 이름은 \"딜러\"와 같을 수 없습니다."),
    ERROR_DUPLICATE_NAME("플레이어에 중복된 이름이 있습니다.");

    private String message;

    Message(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}