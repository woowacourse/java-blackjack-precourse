package domain.user;

import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Player {
    public Dealer() {
        super("딜러", 0);
    }

    // TODO 추가 기능 구현
    public void showMoney(List<Player> user) {
        int money = 0;

        user.remove(0);
        for (Player player : user) {
            money += player.getBettingMoney();
        }

        System.out.println(super.getName() + " : " + money);
    }

}
