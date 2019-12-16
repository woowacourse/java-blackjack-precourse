package domain.user;

import domain.BenefitMultipleType;
import domain.outcome.Outcome;
import domain.outcome.Outcomes;
import domain.view.InputUtil;
import domain.view.OutputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Users {
    private static List<User> users;
    private static final int EMPTY = 0;

    public Users(List<User> users) {
        valid(users);
        this.users = users;
    }

    private void valid(List<User> users) {
        if(users.size() == EMPTY){
            return;
        }
        throw new IllegalArgumentException("이름을 채워주세요!");
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public static Users initUsers(String[] names) {
        List<User> userList = new ArrayList<>();
        userList.add(new Dealer());
        for (String name : names) {
            // TODO : OutputUtil과의 의존성이 생기는데... 더 나은 방법이 없나 고민해보기
            OutputUtil.printBettingMoneyDemand(name);
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }

    public void receiveBeginningCard() {
        users.forEach(user -> {
            user.addRandomCard();
            user.addRandomCard();
        });
    }

    public void printFinalOutput() {
        users.forEach(user -> {
            user.printFinalOutput();
        });
    }

    public void printInitUserCard() {
        users.forEach(user -> {
            user.printUserInfo();
        });
    }

    public void startAddCardQuestion(Outcomes outcomes) {
        startUserAddCardQuestion(outcomes);
        startDealerAddCardQuestion();
    }

    private void startUserAddCardQuestion(Outcomes outcomes) {
        getPlayers()
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> player.startAddCardLoop());
    }

    private void startDealerAddCardQuestion() {
        users.stream()
                .filter(user -> user.isDealer())
                .map(user -> (Dealer) user)
                .forEach(dealer ->
                        dealer.hit());
    }

    public void decideOutcome(int dealderScore, Outcomes outcomes) {
        decideExcessLoswer(outcomes);
        if (dealderScore > 21) {
            decideAllAlivePlayerWinner(outcomes);
            return;
        }
        decideWinner(dealderScore, outcomes);
        decideLoswer(dealderScore, outcomes);
    }

    public void addOutcomes(Player player, Outcomes outcomes, Double benefit) {
        outcomes.addOutcomes(
                player.getName(),
                benefit, player.getCards());
    }

    private void decideAllAlivePlayerWinner(Outcomes outcomes) {
        getPlayers()
                .filter(player -> !player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_WIN));
                });
    }

    private void decideWinner(int dealerScore, Outcomes outcomes) {
        getPlayers()
                .filter(player -> player.isWinBy(dealerScore))
                .filter(player -> !player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_WIN));
                });
    }

    private void decideExcessLoswer(Outcomes outcomes) {
        getPlayers()
                .filter(player -> player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_LOSE));
                });
    }

    private void decideLoswer(int dealerScore, Outcomes outcomes) {
        getPlayers()
                .filter(player -> !player.isWinBy(dealerScore))
                .filter(player -> !player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_LOSE));
                });
    }

    public Stream<Player> getPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .map(player -> (Player) player);
    }

    public String getUsersName() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> ((Player) user).getName())
                .collect(Collectors.joining(","));
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .get();
    }

    public int getInitBlackJackPlayer() {
        return (int) getPlayers().count();
    }
}
