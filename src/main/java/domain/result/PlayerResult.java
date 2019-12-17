package domain.result;

import domain.user.Player;

public class PlayerResult extends GamerResult {
    private Player player;
    private int cardSize;

    public PlayerResult(Player player) {
        super(player);
        this.player = player;
        this.cardSize = player.getCardSize();
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setResultMoney(Result result) {
        double weight = result.getWeight();
        super.setResultMoney(player.getBettingMoney() * weight);
    }

    public String getName() {
        return player.getName();
    }
}
