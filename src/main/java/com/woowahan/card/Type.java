package com.woowahan.card;

public enum Type {
	SPADE,
	DIAMOND,
	HEART,
	CLUB;

	@Override
	public String toString() {
		String[] visualizedType = {"♠", "♦", "♥", "♣"};
		return visualizedType[this.ordinal()];
	}
}
