package domain.user;

import com.sun.nio.sctp.IllegalReceiveException;

import java.util.ArrayList;
import java.util.List;

import static util.CustomErrorMessage.PLAYER_INPUT_SIZE_ERROR;

public class ActiveGamers {

    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();

    public ActiveGamers(List<String> playerNames, List<Double> playerBettingMoneys) {
        validate(playerNames, playerBettingMoneys);
        for(int index =0; index<playerNames.size(); index++){
            players.add(new Player(playerNames.get(index), playerBettingMoneys.get(index)));
        }
    }

    private void validate(List<String> playerNames, List<Double> playerBettingMoneys) {
        if(playerNames.size() != playerBettingMoneys.size())
            throw new IllegalArgumentException(PLAYER_INPUT_SIZE_ERROR);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
