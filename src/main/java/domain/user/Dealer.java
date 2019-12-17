package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Helper {

    public Dealer() {
    }

    // TODO 추가 기능 구현

    @Override
    public String cardsToString() {
        return "딜러 카드 : " + super.cardsToString();
    }

    public boolean addCardDraw(){
        if(super.scoreCalculator() <= 16){
            return true;
        }
        return false;
    }
}
