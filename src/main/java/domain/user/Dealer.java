package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {

    private static final Integer SIXTEEN = 16;
    private static final double MINUS_ZERO = -1;

    public Dealer() {
    }

    public boolean shouldHaveOneMoreCard() {
        return sumOfCard() <= SIXTEEN;
    }

    /**
     * 플레이어의 수익(승패)에 따라, 딜러의 수익을 결정하는 메서드입니다.
     * @param player
     * @return 해당 플레이어와의 게임에서 수익 리턴
     */
    public double calculateProfit(Player player) {
        if (player.isWinGame(this) || player.isDrawGame(this)) {
            return player.getProfit() * MINUS_ZERO;
        }
        return player.getProfit() * MINUS_ZERO;
    }

}
