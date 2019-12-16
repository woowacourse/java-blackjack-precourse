package com.github.callmewaggs.game.domain.card;

public enum Symbol {
  ACE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(10),
  QUEEN(10),
  KING(10);

  public static final int ANOTHER_ACE_NUMBER = 11;

  private int score;
  private static final int DECIMAL_NUMBER_RADIX = 10;

  Symbol(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public char getFirstLetterOfScore() {
    if (score == 1 || score == 10) {
      return name().charAt(0);
    }
    return Character.forDigit(score, DECIMAL_NUMBER_RADIX);
  }
}
