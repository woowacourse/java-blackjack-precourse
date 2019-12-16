package com.precourse.blackjack.domain.card;

public enum Type {
	SPADE('♠'),
	DIAMOND('◆'),
	HEART('♥'),
	CLUB('♣');

	private char design;

	Type(char design) {
		this.design = design;
	}

	public char getDesign() {
		return design;
	}
}
