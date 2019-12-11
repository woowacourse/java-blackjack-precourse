package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

/**
 * BlackJack 클래스는 블랙잭 게임을 실행하고, 유저/딜러와 카드간의 연계, 배팅 등을 처리해주는 클래스이다.
 *
 * 이 클래스는 싱글톤 기법으로 설계되었다.
 * 프로그램 설계 상 여러 블랙잭 게임이 동시에 실행될 수 없으므로,
 * 하나의 블랙잭 게임을 static으로 생성해 두고 이를 활용하게 설계하였다.
 * 본 프로젝트에는 동기화 이슈가 없지만, 추후 재사용하거나 참고할 떄를 대비하여
 * 별도의 Holder 클래스를 둬 멀티쓰레딩 환경에서 여러 개가 생성되지 않도록 하였다.
 *
 * @author kafka
 * @version 1.0
 */

public class BlackJack {
    private List<Player> playerList;
    private Dealer dealer;
    private List<Card> cardList;
    private int cardIterator;

    private BlackJack() {
        throw new AssertionError();
    }

    private static class BlackJackHolder {
        public static final BlackJack INSTANCE = new BlackJack();
    }

    public static BlackJack getInstance(){
        return BlackJackHolder.INSTANCE;
    }
    public void initBlackJack() {
        cardList = CardFactory.create();
        cardIterator = 0;
        dealer = new Dealer();
        playerList = createPlayerList();
    }

    /**
     * drawCard 메서드는 카드를 한 장 뽑아 반환하고, iterator 값을 올려준다.
     * 카드의 리스트는 생성시 셔플되므로, 이를 선형으로 순회하게 되면
     * 중복 없이 랜덤한 카드가 뽑히는 것을 보장할 수 있다.
     *
     * @return 뽑힌 카드를 반환해준다.
     * @exception AssertionError 뽑을 카드가 없는 경우, 논리적 에러를 생성한다.
     */
    public Card drawCard() {
        if(cardIterator >= cardList.size()) {
            System.out.print(Message.ERROR_CARD_EMPTY);
            throw new AssertionError();
        }
        return cardList.get(cardIterator++);
    }

    public List<Player> createPlayerList() {
        List<Player> playerList;

        return playerList;
    }

    public void playGame() {

    }
}
