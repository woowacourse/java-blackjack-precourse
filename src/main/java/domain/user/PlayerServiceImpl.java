package domain.user;

import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.Deck;

public class PlayerServiceImpl implements PlayerService {
    private UserInterface userInterface;
    private BlackjackPrinter blackjackPrinter;
    private Deck deck;
    @Override
    public void confirmCards(Player player) {
        String willing = userInterface.getWilling();
        //todo: refac
        while (willing.equals("y")) {
            //todo: rafac
            if (!isAvailableToPick()) {
                return;
            }

            player.addCard(deck.pick());
            blackjackPrinter.printUserState(player);
        }
    }
    private boolean isAvailableToPick() {
        return false;
    }
}
