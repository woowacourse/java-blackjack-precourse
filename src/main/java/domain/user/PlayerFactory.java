package domain.user;

import java.util.List;

public interface PlayerFactory {
    List<Player> create(String[] names);
}
