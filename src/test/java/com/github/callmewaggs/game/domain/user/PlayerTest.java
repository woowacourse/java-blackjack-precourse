package com.github.callmewaggs.game.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @DisplayName("플레이어의 이름의 길이가 0인 경우 예외를 던진다.")
  @Test
  public void throw_exception_when_length_of_players_name_is_zero() {
    // Arrange
    String name = "";
    String bettingMoney = "0";

    // Act
    IllegalArgumentException actual =
        assertThrows(
            IllegalArgumentException.class,
            () -> Player.createPlayerIfValidate(name, bettingMoney));

    // Assert
    assertEquals("잘못된 이름입니다. 다시 입력해주세요.", actual.getMessage());
  }

  @DisplayName("플레이어의 베팅 금액이 음수인 경우 예외를 던진다.")
  @Test
  public void throw_exception_when_betting_money_of_player_is_under_zero() {
    // Arrange
    String name = "good";
    String bettingMoney = "-10000";

    // Act
    IllegalArgumentException actual =
        assertThrows(
            IllegalArgumentException.class,
            () -> Player.createPlayerIfValidate(name, bettingMoney));

    // Assert
    assertEquals("배팅 금액이 잘못되었습니다. 다시 입력해주세요.", actual.getMessage());
  }

  @DisplayName("플레이어의 베팅 금액이 숫자가 아닌 경우 예외를 던진다.")
  @Test
  public void throw_exception_when_betting_money_of_player_is_not_a_number() {
    // Arrange
    String name = "good";
    String bettingMoney = "not a number";

    // Act
    IllegalArgumentException actual =
        assertThrows(
            IllegalArgumentException.class,
            () -> Player.createPlayerIfValidate(name, bettingMoney));

    // Assert
    assertEquals("배팅 금액이 잘못되었습니다. 다시 입력해주세요.", actual.getMessage());
  }

  @DisplayName("이름과 배팅 금액에 예외가 없을 경우 플레이어를 생성한다.")
  @Test
  public void create_player_when_there_is_no_exception() {
    // Arrange
    String name = "goodname";
    String bettingMoney = "10000";

    // Act
    Player actual = Player.createPlayerIfValidate(name, bettingMoney);

    // Assert
    assertEquals(name, actual.getName());
    assertEquals(Double.parseDouble(bettingMoney), actual.getBettingMoney());
  }
}
