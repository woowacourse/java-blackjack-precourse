package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.BlackjackRule;
import com.github.callmewaggs.game.IOHelper;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Participant {

  private static final String INPUT_YES = "y";
  private static final String INPUT_NO = "n";
  private final String name;
  private final long bettingMoney;

  private Player(String name, long bettingMoney) {
    this.name = name;
    this.bettingMoney = bettingMoney;
  }

  // TODO : 2depth
  @Override
  public boolean hitOrStay() {
    String playerInput = "";
    boolean lessThanBlackjackNumber = checkCurrentScoreLessThanBlackjackNumber();
    if (lessThanBlackjackNumber) {
      while (true) {
        playerInput = IOHelper.inputHitOrStay(this.getName());
        if (playerInput.equals(INPUT_YES) || playerInput.equals(INPUT_NO)) {
          break;
        }
        IOHelper.printExceptionMessage("y 또는 n만 입력 가능합니다. 다시 입력해주세요.");
      }
    }
    return playerInput.equals(INPUT_YES);
  }

  private boolean checkCurrentScoreLessThanBlackjackNumber() {
    if (super.getCurrentScore() > BlackjackRule.BLACKJACK_NUMBER) {
      IOHelper.printHitRejectedMessage(this.getName());
      return false;
    }
    return true;
  }

  @Override
  public String getName() {
    return name;
  }

  public long getBettingMoney() {
    return bettingMoney;
  }

  public static Player createPlayerIfValidate(String name, String bettingMoney) {
    checkNameValidation(name);
    long money = checkMoneyValidation(bettingMoney);
    return new Player(name, money);
  }

  private static void checkNameValidation(String name) {
    checkValidationWithCondition(name.length() == 0, "잘못된 이름입니다. 다시 입력해주세요.");
  }

  private static long checkMoneyValidation(String bettingMoney) {
    long money = parseStringMoneyToLong(bettingMoney);
    checkValidationWithCondition(money < 0, "배팅 금액이 잘못되었습니다. 다시 입력해주세요.");
    return money;
  }

  private static long parseStringMoneyToLong(String bettingMoney) {
    try {
      return Long.parseLong(bettingMoney);
    } catch (Exception e) {
      throw new IllegalArgumentException("배팅 금액이 잘못되었습니다. 다시 입력해주세요.");
    }
  }

  private static void checkValidationWithCondition(boolean condition, String message) {
    if (condition) {
      throw new IllegalArgumentException(message);
    }
  }

}
