package domain.card;

import domain.util.ConstMessage;

import java.util.List;

public class Deck {
    /**
     * cardIterator는 덱을 순회하는 번지값 저장 변수이다.
     * 우리는 섞여있는 52장의 카드 리스트를 가지고 있으며, 이는 변경 불가하다.(뽑은 카드 pop 불가)
     * 그렇기 때문에, 대신 선형으로 순회하며 카드를 전달하는 식으로 중복을 피하고 랜덤한 뽑기를 구현한다.
     */
    private int cardIterator;

    /**
     * cardList는 카드 한 덱(52장)의 뭉치이다.
     * cardFactory의 create 메소드를 통해 섞인 52장의 카드가 입력된다.
     */
    private List<Card> cardList;

    /**
     * Deck은 생성자 메서드이다.
     * 새로운 덱을 CardFactory로부터 받고, 이터레이터를 초기화한다.
     */
    public Deck() {
        cardList = CardFactory.create();
        cardIterator = 0;
    }
    /**
     * drawCard 메서드는 카드를 한 장 뽑아 반환하고, iterator 값을 올려준다.
     * 카드의 리스트는 생성시 셔플되므로, 이를 선형으로 순회하게 되면
     * 중복 없이 랜덤한 카드가 뽑히는 것을 보장할 수 있다.
     * <p>
     * 카드가 다 떨어지는 극단적인 경우(예를 들어 20명 이상의 플레이어가 3장씩 뽑는다던가 하는 경우)를 고려하여
     * 카드가 떨어지면 예외를 던지도록 구현하였다.
     * 다 떨어졌을 때 새로운 덱을 create하는 것도 고려하였으나,
     * 덱이 다 떨어지는 상황은 예외적 상황으로 보는 것이 더 올바르다고 판단하였다.
     *
     * @return 뽑힌 카드를 반환해준다.
     * @throws AssertionError 뽑을 카드가 없는 경우, 논리적 에러를 생성한다.
     */
    public Card drawCard() {
        if (cardIterator >= cardList.size()) {
            System.out.print(ConstMessage.ERROR_CARD_EMPTY);
            throw new AssertionError();
        }
        return cardList.get(cardIterator++);
    }
}
