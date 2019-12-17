package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import annotation.TestDescription;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @TestDescription("각각의 유저생성에 에러가 없으며, 수익금을 구하는 메서드가 작동하는지 테스트합니다.")
    public void constructorTest() {
        Player player = new Player("piglet", 30000);
        Player player2 = new Player("hello", 30000);
        Player player3 = new Player("world", 30000);
        assertEquals(player.getName(), "piglet");
        assertEquals(player2.getName(), "hello");
        assertEquals(player3.getName(), "world");

        double bettingMoney = player.getProfit();
        player.multiplyProfit(1.5);
        double profit = player.getProfit();
        assertEquals(bettingMoney * 1.5, profit);

        double bettingMoney2 = player2.getProfit();
        player2.multiplyProfit(0);
        double profit2 = player2.getProfit();
        assertEquals(bettingMoney2 * 0, profit2);

        double bettingMoney3 = player3.getProfit();
        player3.multiplyProfit(-1);
        double profit3 = player3.getProfit();
        assertEquals(bettingMoney3 * -1, profit3);
    }

}