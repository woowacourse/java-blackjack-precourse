package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User{
    private static final int MINIMUM_LENGTH_OF_PLAYER_NAME = 1;
    private static final int MINIMUM_BETTING_SIZE = 0;

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        validateName(name);
        validateBettingMoney(bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validateName(String name) {
        if (name.length() < MINIMUM_LENGTH_OF_PLAYER_NAME) {
            throw new IllegalArgumentException("각 플레이어의 이름은 1자 이상만 가능합니다.");
        }
    }

    private void validateBettingMoney(double bettingMoney) {
        if (bettingMoney <= MINIMUM_BETTING_SIZE) {
            throw new IllegalArgumentException("베팅 금액은 0을 초과해야 합니다.");
        }
    }

    @Override
    public String getCardInfo() {
        return this.name + "의 카드: " + super.getCardInfo();
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }
}
