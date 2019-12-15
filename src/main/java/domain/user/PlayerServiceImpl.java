package domain.user;

import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.CardConfig;
import domain.card.Deck;
import domain.errors.InvalidInputException;

public class PlayerServiceImpl implements PlayerService {
    private UserInterface userInterface;
    private BlackjackPrinter blackjackPrinter;
    private Deck deck;
    @Override
    public void confirmCards(Player player) {
        Will will = userInterface.getWillForMoreCard();
        while (will.equals(Will.Hit)) {
            //todo: rafac
            if (!isAvailableToPick(player)) {
                throw new InvalidInputException("더이상 카드를 받을 수 없습니다.");
            }

            player.addCard(deck.pick());
            blackjackPrinter.printUserState(player);
            will = userInterface.getWillForMoreCard();
        }
    }
    private boolean isAvailableToPick(Player player) {
        int sum = player.calculateSumOfCards();
        return sum < CardConfig.BLACKJACK;
    }
}
