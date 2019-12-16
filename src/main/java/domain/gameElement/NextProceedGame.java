package domain.gameElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import domain.user.User;

public class NextProceedGame {
    public boolean nextProceed(List<User> users, int step) {
        if (step == 1) {
            return ScoreCheckerWithBlackJack(users);
        }
        return ScoreChecker(users);
    }

    private boolean ScoreCheckerWithBlackJack(List<User> users) {
        Set<Boolean> stopSet = new HashSet<Boolean>();
        for (User user : users) {
            stopSet.add(user.blackJackYN(user.getSumNumbers()));
            stopSet.add(user.bustYN(user.getSumNumbers()));
        }
        return !stopSet.contains(true);
    }

    private boolean ScoreChecker(List<User> users) {
        Set<Boolean> stopSet = new HashSet<Boolean>();
        for (User user : users) {
            stopSet.add(user.twentyoneYN(user.getSumNumbers()));
            stopSet.add(user.bustYN(user.getSumNumbers()));
        }
        return !stopSet.contains(true);
    }

}
