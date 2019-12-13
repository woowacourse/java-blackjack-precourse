package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {

    @DisplayName("베팅 금액 생성")
    @Test
    void make() {
        BettingMoney bettingMoney = new BettingMoney(10000D);
        assertThat(bettingMoney).isEqualTo(new BettingMoney(10000D));
    }

    @DisplayName("블랙잭인 경우")
    @Test
    void blackJack() {
        BettingMoney bettingMoney = new BettingMoney(10000D);
        double blackJackRate = 1.5;
        assertThat(bettingMoney.blackJack()).isEqualTo(10000D * blackJackRate);
    }

    @DisplayName("푸시인 경우")
    @Test
    void push() {
        BettingMoney bettingMoney = new BettingMoney(10000D);
        assertThat(bettingMoney.push()).isEqualTo(0D);
    }

    @DisplayName("일반 승리")
    @Test
    void earn() {
        BettingMoney bettingMoney = new BettingMoney(10000D);
        assertThat(bettingMoney.earn()).isEqualTo(10000D);
    }

    @DisplayName("패배")
    @Test
    void lose() {
        BettingMoney bettingMoney = new BettingMoney(10000D);
        double loseRate = -1;
        assertThat(bettingMoney.lose()).isEqualTo(10000D * loseRate);
    }
}