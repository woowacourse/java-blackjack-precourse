import com.github.callmewaggs.game.BlackjackGame;
import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardFactory;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Participant;
import com.github.callmewaggs.game.domain.user.Player;
import com.github.callmewaggs.game.domain.user.PlayerFactory;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGameMainApp {

  public static void main(String[] args) {
    List<Player> players = PlayerFactory.preparePlayers();
    List<Participant> participants = new ArrayList<>(players);
    participants.add(new Dealer());

    List<Card> cards = CardFactory.create();

    BlackjackGame blackjackGame = new BlackjackGame(participants, cards);
    blackjackGame.gameStart();
  }
}
