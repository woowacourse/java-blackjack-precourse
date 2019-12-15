package domain.game;

import domain.input.Input;
import domain.output.Output;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

/**
 * BlackJackGame
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class BlackJackGame {
    public static final int MAX_Score = 21;
    public static final int DEALER_MIN_SCORE = 17;
    public static final int ACE_WEIGHT = 10;
    public static final int CARD_COUNT = 52;

    private List<Player> players;
    private Dealer dealer = new Dealer();
    private Input input = new Input();
    private Output output = new Output();
    private TakingCard takingCard = new TakingCard();

    public void blackJackGame() {
        takingCard.initialize();
        players = input.inputPlayerNames();
        takingCard.takeFirstCards(players, dealer);
        output.printFirstInputFinish(players);
        output.printCards(players, dealer, false);
        takingCard.takeMorePlayerCard(players);
        takingCard.takeMoreDealerCard(dealer);
        output.printCards(players, dealer, true);
        output.printResult(players, dealer);
    }
}
