package application.util.random;

import application.domain.card.CardFactory;
import application.util.IndexGenerator;

public class RandomIndexGenerator implements IndexGenerator {
    private static final int CARDS_SIZE = CardFactory.create().size();

    @Override
    public int getIndex() {
        return (int) Math.random() * CARDS_SIZE;
    }
}
