package application.domain.user;

import application.domain.card.CardSupplier;
import application.view.Output;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<Player> players;
    private final Dealer dealer;
    private List<User> users = new ArrayList<>();

    public Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        this.users.addAll(players);
        this.users.add(dealer);
    }

    @Override
    public String toString() {
        return "Users{" +
                "players=" + players;// +
                //", dealer=" + dealer +
                //'}';
    }

    public void addTwoCards(CardSupplier supplier) {
        for (User user : users) {
            user.addCard(supplier.supply());
            user.addCard(supplier.supply());
            Output.printFirstCardInfo(user);
        }
    }

    public void checkCardsAndAddCard(CardSupplier supplier) {
        for (User user : users) {
            user.checkCardAndAddCard(supplier);
        }
    }
}
