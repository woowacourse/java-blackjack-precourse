package com.woowacourse.blackjack.domain.card;

/**
 * 카드의 무늬를 의미하는 열거형
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클럽");

    private String name;

    Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
