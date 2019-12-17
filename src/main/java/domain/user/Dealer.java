package domain.user;

/**
 * @ClassName : Dealer
 * @ClassExplanation : 게임 딜러를 의미하는 클래스
 * @author : Kim SeYun
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
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
