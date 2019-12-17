package domain.user;

import domain.card.Card;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * 플레이어의 이름을 리턴하는 getter
     *
     * @return 플레이어의 이름을 리턴
     */
    public String getName() {
        return this.name;
    }

    /**
     * 플레이어가 갖은 카드List를 리턴하는 getter
     *
     * @return 플레이어가 갖은 카드List
     */
    public String toStringCard() {
        List<String> cardInfo = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            cardInfo.add(cards.get(i).toString());
        }

        return this.name +
                "카드: " +
                String.join(",", cardInfo);
    }

    /**
     * 플레이어가 갖고있는 symbol의 합을 계산
     *
     * @return 총 symbol
     */
    public int calculateSymbol() {
        int symbolSum = 0;
        List<Integer> symbol = makeListSymbol();

        symbol = symbol.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        symbolSum = calculateCase(symbol,symbolSum);

        return symbolSum;
    }

    /**
     * 플레이어의 카드객체에 ace존재유무 확인하고 더하는 경우를 나눔
     * ACE가 없을경우 카드list의 추가된 마지막 객체의 symbol값을 그냥 더함
     *
     * @param symbolSum symbol의 총합
     * @return symbol의 총합
     */
    public int calculateCase(List<Integer> symbol,int symbolSum) {
        if (cards.stream().anyMatch(card -> card.getSymbol() == 1)) {
            symbolSum = calculateAutoAce(symbol,symbolSum);
            return symbolSum;
        }
        for (Integer integer : symbol) {
            symbolSum += integer;
        }
        return symbolSum;
    }

    /**
     * card 객체에서 ace가 있는경우와 없는 경우를 자동으로 계산해주는 가능
     * card의 각 심볼을 내림차순으로 정렬하고 symbol만 모인 리스트를 새로만든다
     *
     * @param symbolSum symbol의 합을 전달해주기 위한 파라미터
     * @return symbolSum을 계산하여 리턴
     */
    public int calculateAutoAce(List<Integer> symbol,int symbolSum) {
        final int blackjack = 21;
        final int aceBigNumber = 11;
        final int aceLittleNumber = 1;

        for (int i = 0; i < symbol.size(); i++) {
            symbolSum = aceCase(symbol.get(i), symbolSum, blackjack, aceBigNumber, aceLittleNumber);
        }
        return symbolSum;
    }

    /**
     * ACE가 존재할 경우 경우를 나눠서 11로 계산할지 1로 계산할지 정하는 기능
     *
     * @param symbol card의 각 심볼을 내림차순으로 정렬한 리스트
     * @param symbolSum 심볼의 총합을 구하기위한 변수
     * @param blackjack 버스트 조건인 final변수
     * @param aceBigNumber ACE를 11로 계산할 경우의 final변수
     * @param aceLittleNumber ACE를 1로 계산할 경우의 final변수
     * @return
     */
    public int aceCase(Integer symbol, int symbolSum, int blackjack, int aceBigNumber, int aceLittleNumber) {
        final int ace = 1;

        if (symbol != ace) {
            return symbolSum + symbol;
        }

        if ((symbolSum + aceBigNumber) > blackjack) {
            return symbolSum + aceLittleNumber;
        }
        return symbolSum + aceBigNumber;
    }

    /**
     * symbol값만 있는 리스트 생성
     *
     * @return symbol list
     */
    public List<Integer> makeListSymbol() {
        List<Integer> symbol = new ArrayList<>();

        for (Card card : cards) {
            symbol.add(card.getSymbol());
        }
        return symbol;
    }

    public double getbettingMoney(){
        return this.bettingMoney;
    }
}