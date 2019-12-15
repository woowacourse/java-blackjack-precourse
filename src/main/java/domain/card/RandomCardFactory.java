package domain.card;

import java.util.List;
import java.util.Random;

public class RandomCardFactory {

    public static Card create() {
        Random random = new Random();
        List<Card> allCards = AllCard.getCards();
        return allCards.get(random.nextInt(allCards.size()));
    }

}
