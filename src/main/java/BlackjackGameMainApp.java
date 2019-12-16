import com.github.callmewaggs.game.BlackjackGame;
import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardFactory;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import com.github.callmewaggs.game.domain.user.PlayerFactory;
import java.util.List;

public class BlackjackGameMainApp {

  public static void main(String[] args) {
    Dealer dealer = new Dealer();
    List<Player> players = PlayerFactory.preparePlayers();

    List<Card> cards = CardFactory.create();

    BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cards);
    blackjackGame.gameStart();
  }
}
