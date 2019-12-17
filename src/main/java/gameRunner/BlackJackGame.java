package gameRunner;

import domain.card.Card;
import domain.card.CardFactory;
import domain.result.ResultGamersScore;
import domain.user.ActiveGamers;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import view.InputView;

import java.util.List;
import java.util.stream.IntStream;

import static view.OutputView.*;

public class BlackJackGame {

    private final int INITIAL_GIVING_CARD_COUNT = 2;
    private final String ANSWER_END_COMMAND = "n";

    private ActiveGamers activeGamers;
    private CardFactory cardFactory;

    public BlackJackGame(final List<String> names, final List<Double> bettingMoneys) {
        this.activeGamers = new ActiveGamers(names, bettingMoneys);
        this.cardFactory = new CardFactory();
    }

    public void giveInitCard() {
        IntStream.range(0, INITIAL_GIVING_CARD_COUNT).boxed()
                .forEach(x -> giveInitCardToGamers());
        showGiveCardToEachInfo(activeGamers.getPlayersName());
        showGamersCard(activeGamers);
    }

    private void giveInitCardToGamers() {
        List<Card> givingCards = cardFactory.popRandomCard(activeGamers.size());
        this.activeGamers.addCardToEveryOne(givingCards);
    }

    public void giveMoreCardToGamers(InputView inputView) {
        for (Player player : activeGamers.getPlayers()) {
            questionGiveCardToPlayer(player, inputView);
        }
        questionGiveCardToDealer(activeGamers.getDealer());
        showGamersCardWithScore(activeGamers);
    }

    private void questionGiveCardToDealer(Dealer dealer) {
        if (dealer.isDealerGetMoreCard()) {
            showGetMoreCardForDealer();
            giveCardToGamer(dealer);
        }
    }

    private void questionGiveCardToPlayer(Player player, InputView inputView) {
        String yesOrNo = inputView.getYesOrNo(player);
        while (!yesOrNo.equals(ANSWER_END_COMMAND)) {
            giveCardToGamer(player);
            showOneGamerCard(player);
            yesOrNo = inputView.getYesOrNo(player);
        }
        showOneGamerCard(player);
    }

    private void giveCardToGamer(Gamer gamer) {
        gamer.addCard(cardFactory.popRandomCard());
    }

    public void giveFinalResult() {
        ResultGamersScore resultGamersScore = new ResultGamersScore(activeGamers);
        showResult(resultGamersScore);
    }

}
