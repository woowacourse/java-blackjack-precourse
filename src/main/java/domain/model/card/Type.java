/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.card;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote Card 객체에 사용될 Type enum입니다.
 * @since : 2019.12.17 화요일
 */
public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private String koreanType;

    Type(String koreanType) {
        this.koreanType = koreanType;
    }

    public String getType() {
        return koreanType;
    }
}
