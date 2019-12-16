package domain.user;


public class PlayerFactory {
    public Player create(String name, double bettingMoney) {
        return new Player(name, bettingMoney);
    }
}
