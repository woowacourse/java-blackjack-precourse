package domain.user;

import domain.outcome.Outcomes;
import view.InputUtil;
import view.OutputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Users {
    private static List<User> users;
    private static final int EMPTY = 0;

    private Users(List<User> users) {
        valid(users);
        Users.users = users;
    }

    private void valid(List<User> users) {
        if (users.size() != EMPTY) {
            return;
        }
        throw new IllegalArgumentException("이름을 채워주세요!");
    }

    public static Users initUsers(String[] names) {
        List<User> userList = new ArrayList<>();
        addDealer(userList);
        for (String name : names) {
            printBettingMoneyDemand(name);
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }

    private static void addDealer(List<User> userList) {
        userList.add(new Dealer());
    }

    private static void printBettingMoneyDemand(String name) {
        OutputUtil.printBettingMoneyDemand(name);
    }

    public void receiveBeginningCard() {
        users.forEach(User::addInitCard);
    }

    public void startAddCardQuestion(Outcomes outcomes) {
        startUserAddCardQuestion(outcomes);
        startDealerAddCard();
    }

    private void startUserAddCardQuestion(Outcomes outcomes) {
        getPlayer()
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(Player::startAddCardLoop);
    }

    private void startDealerAddCard() {
        getDealer().hit();
    }

    public void decideOutcome(int dealderScore, Outcomes outcomes) {
        decideExcessOutcome(true, false, outcomes);
        if (dealderScore > 21) {
            decideExcessOutcome(false, true, outcomes);
            return;
        }
        decideWinOrLose(true, dealderScore, outcomes);
        decideWinOrLose(false, dealderScore, outcomes);
    }

    private void decideExcessOutcome(
            boolean checkExcessFlag,
            boolean winFlag,
            Outcomes outcomes
    ) {
        getPlayer()
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .filter(player -> player.checkExcess() == checkExcessFlag)
                .forEach(player -> {
                    addOutcome(player, outcomes,
                            player.calcureateBenefit(winFlag));
                });
    }

    private void decideWinOrLose(
            boolean winFlag,
            int dealerScore,
            Outcomes outcomes
    ) {
        getPlayer()
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .filter(player -> player.isWinBy(dealerScore) == winFlag)
                .filter(player -> !player.checkExcess())
                .forEach(player -> {
                    addOutcome(player, outcomes,
                            player.calcureateBenefit(winFlag));
                });
    }

    private void addOutcome(Player player, Outcomes outcomes, Double benefit) {
        outcomes.addOutcomes(
                player.getName(),
                benefit, player.getCards());
    }

    public Stream<Player> getPlayer() {
        return users.stream()
                .filter(User::isPlayer)
                .map(player -> (Player) player);
    }

    public String getUsersName() {
        return users.stream()
                .filter(User::isPlayer)
                .map(user -> ((Player) user).getName())
                .collect(Collectors.joining(","));
    }

    public Dealer getDealer() {
        Optional<Dealer> dealer = users.stream()
                .filter(User::isDealer)
                .map(user -> (Dealer) user)
                .findFirst();
        if (dealer.isPresent()) {
            return dealer.get();
        }
        throw new IllegalStateException("딜러 없는 경우가 발생하였습니다.");
    }

    public int getInitBlackJackPlayer() {
        return (int) getPlayer()
                .filter(User::isBlackJack)
                .count();
    }

    public void printBeginningUserCard() {
        users.forEach(User::printUserInfo);
    }

    public void printFinalOutput() {
        users.forEach(User::printFinalOutput);
    }
}
