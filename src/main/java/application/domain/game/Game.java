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
}
