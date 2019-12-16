package domain.user;

import java.util.List;

public abstract class UserFactory<T> {

//    abstract List<T> create(String[] names);
//    abstract T create();

    public Player create(String name, int bettingMoney) {
        return new Player(name, bettingMoney);
    }
}
