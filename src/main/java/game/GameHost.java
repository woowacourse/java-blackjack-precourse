package game;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Entry;
import domain.user.Player;
import util.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameHost {
    private InputView inputView;
    private OutputView outputView;
    private Dealer dealer = new Dealer();
    private List<Player> participants = new ArrayList<>();
    private CardDeck cardList = new CardDeck();

    public GameHost(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void setParticipants(List<String> name, List<Double> money) {
        for (int i = 0; i < name.size(); i++) {
            participants.add(new Player(name.get(i), money.get(i)));
        }
    }

    public void start() {
        if (participants.size() <= GameConstants.ENTRY_MIN) {
            OutputView.printNoEntry();
            return;
        }

        // 최초 카드 분배
        for (int i = 0; i < GameConstants.FIRST_DIVIDE_COUNT; i++) {
            divideCardToAll();
        }
        outputView.printDivideResult(dealer, participants);
        requestMoreCard();
        processGame();
        showResult();
    }

    private void divideCardToAll() {
        divideCardToEntry(dealer);
        for (Player participant : participants) {
            divideCardToEntry(participant);
        }
    }

    private void divideCardToEntry(Entry entry) {
        cardList.resetCardIfNotExist();
        entry.addCard(cardList.drawNewCard());
    }

    private void processGame() {
        for (Player player : participants) {
            processMoney(player);
        }
    }

    private void processMoney(Player player) {
        if (player.isWinner(dealer.getScore())) {
            player.addProfits(player.getBettingMoney());
            dealer.addProfits(-player.getBettingMoney());
        }
        if (player.isLoser(dealer.getScore())) {
            player.addProfits(-player.getBettingMoney());
            dealer.addProfits(player.getBettingMoney());
        }
    }

    private void showResult() {
        outputView.printResultOfEntry(dealer);
        for (Player participant : participants) {
            outputView.printResultOfEntry(participant);
        }
        outputView.printResultMoney(dealer, participants);
    }

    private void requestMoreCard() {
        for (Player player : participants) {
            requestCardToPlayer(player);
        }
        requestCardToDealer();
    }

    private void requestCardToPlayer(Player player) {
        boolean isStop = true;
        if (player.isBlackjack()) {
            return;
        }

        while (!checkBustState(player) && isStop) {
            isStop = requestChoice(player);
            addCardIfWants(player, isStop);
        }
    }

    private void requestCardToDealer() {
        while (dealer.getScore() <= GameConstants.REQUEST_MORE_CARD_COUNT) {
            divideCardToEntry(dealer);
            outputView.printDealerAddState(dealer.getScore());
        }
        checkBustState(dealer);
    }

    private boolean requestChoice(Player player) {
        String needsMoreCard;

        do {
            needsMoreCard = inputView.inputMoreCard(player.getName());
        } while (!InputValidator.isCorrectChoice(needsMoreCard));

        return needsMoreCard.equals(GameConstants.YES);
    }

    private void addCardIfWants(Player player, boolean needsMoreCard) {
        if (needsMoreCard) {
            divideCardToEntry(player);
            outputView.printCardState(player);
        }
    }

    private boolean checkBustState(Entry entry) {
        if (entry.getScore() > GameConstants.BLACKJACK) {
            OutputView.printBust();
            return true;
        }
        return false;
    }
}
