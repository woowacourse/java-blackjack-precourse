package domain.card;

import java.util.List;
import java.util.Random;

public class RandomCardFactory {

    public static Card create(List<Card> cards) {
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));
    }

}
