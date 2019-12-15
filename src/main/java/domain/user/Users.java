package domain.user;

import java.util.Collections;
import java.util.List;

public class Users {
    private static List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public static List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
