package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {

    public Dealer() {
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    // TODO 추가 기능 구현
}
