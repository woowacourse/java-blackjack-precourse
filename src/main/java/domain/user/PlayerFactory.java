package domain.user;


public class PlayerFactory {
    public Player create(String name, int bettingMoney) {
        return new Player(name, bettingMoney);
    }
}
