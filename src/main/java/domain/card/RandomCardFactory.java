package domain.card;

class RandomCardFactory {
    static Card create() {
        return AllCard.pick();
    }
}
