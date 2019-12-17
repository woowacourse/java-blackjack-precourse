package domain.gameElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import domain.user.User;

public class NextProceedGame {
    public boolean nextProceed(List<User> users) {
        return ScoreChecker(users);
    }

    private boolean ScoreChecker(List<User> users) {
        Set<Boolean> stopSet = new HashSet<Boolean>();
        for (User user : users) {
            stopSet.add(user.stopGameYN(user.getSumNumbers()));
        }
        return !stopSet.contains(true);
    }

}
