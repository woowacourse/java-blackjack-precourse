package domain.result;

import domain.user.Dealer;

import static domain.result.ResultGamersScore.BLACK_JACK_NUMBER;

public class DealerResult extends GamerResult {
    private Dealer dealer;

    public DealerResult(Dealer dealer) {
        super(dealer);
        this.dealer = dealer;
    }

    public boolean isDealerOver() {
        return getBlackJackScore() > BLACK_JACK_NUMBER;
    }

    public String getName() {
        return dealer.getName();
    }
}
