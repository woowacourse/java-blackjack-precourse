package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {

    private static final Integer SIXTEEN = 16;
    private static final double MINUS_ZERO = -1;

    public Dealer() {
    }

    // TODO 추가 기능 구현

    public boolean shouldHaveOneMoreCard() {
        return sumOfCard() <= SIXTEEN;
    }

    public double calculateProfit(Player player) {
        if (player.isWinGame(this) || player.isDrawGame(this)) {
            return player.getProfit() * MINUS_ZERO;
        }
        return player.getProfit() * MINUS_ZERO;
    }

}
