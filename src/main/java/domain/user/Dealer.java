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
    public void showMoney(List<Player> winner, List<Player> loser) {
        int money = 0;

        for (Player player : loser) {
            money += player.getBettingMoney();
        }
        for (Player player : winner) {
            money -= player.getBettingMoney();
        }

        System.out.println(super.getName() + " : " + money);
    }

    @Override
    public void showCards() {
        System.out.println(super.getName() + " : " + super.getCard().get(0));
    }
}
