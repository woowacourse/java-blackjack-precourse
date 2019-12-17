package application.domain.game;

import application.domain.card.CardSupplier;
import application.domain.user.Users;

public class Game {
    private final Users users;
    private final CardSupplier supplier;

    public Game(Users users, CardSupplier cardSupplier) {
        this.users = users;
        this.supplier = cardSupplier;
    }

    public void play() {
        giveTwoCardsToAllPlayers();
        checkAddableCardAndAddCard();
    }

    private void giveTwoCardsToAllPlayers() {
        users.addTwoCards(supplier);
    }

    private void checkAddableCardAndAddCard() {
        users.checkCardsAndAddCard(supplier);
    }
}
