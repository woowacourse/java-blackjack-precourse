package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private double bettingMoney;

    public String getName() {
        return name;
    }

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    public void multiplyProfit(double multiple) {
        bettingMoney *= multiple; // 배팅금액에 수익률을 곱해, 수익을 계산합니다.
    }

    public double getProfit() {
        return bettingMoney;
    }

    public boolean isWinGame(Dealer dealer) { // 딜러와의 게임에서 이긴 경우입니다.
        if (dealer.isBurst() && !isBurst())
            return true;
        if (dealer.sumOfCard() < sumOfCard() && !dealer.isBurst() && !isBurst())
            return true;
        return false;
    }

    public boolean isDrawGame(Dealer dealer) { // 비긴 경우입니다. 이긴경우와 비긴경우를 제외하면 진 경우이기에 진 경우는 넣지 않았습니다.
        if (dealer.sumOfCard() == sumOfCard()
                && !dealer.isBurst() && !isBurst())
            return true;
        return false;
    }
}
