package domain.card;

import java.util.List;
import java.util.Random;

public class RandomCardFactory {

    public static Card create() {
        Random random = new Random();
        List<Card> allCards = AllCard.getCards();
        Card card = allCards.get(random.nextInt(allCards.size()));
        AllCard.remove(card); // 중복을 제거하기 위한 카드 제거
        return card;
    }
}
