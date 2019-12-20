package domain.user;

import domain.card.Score;
import domain.outcome.OutcomeType;
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
    private static final int LIMIT = 21;
    private static final int EMPTY = 0;
    private static final char COMMA = ',';

    public Users(List<User> users) {
        validAllNameEmpty(users);
        Users.users = users;
    }

    private void validAllNameEmpty(List<User> users) {
        if (users.size() != EMPTY) {
            return;
        }
        throw new IllegalArgumentException("이름을 채워주세요!");
    }

    public static Users initUsers(String nameInput) {
        validEmptyNameUser(nameInput);
        List<User> userList = new ArrayList<>();
        addDealer(userList);
        String[] names = nameInput.split(String.valueOf(COMMA));
        for (String name : names) {
            OutputUtil.printBettingMoneyDemand(name);
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }

    private static void validEmptyNameUser(String name) {
        if (name.split(String.valueOf(COMMA)).length == countComma(name) + 1) {
            return;
        }
        throw new IllegalArgumentException("쉼표를 너무 많이 사용하셨어요!");
    }

    private static int countComma(String inputString) {
        return (int) inputString.chars()
                .filter(value -> value == COMMA)
                .count();
    }

    private static void addDealer(List<User> userList) {
        userList.add(new Dealer());
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

    public void decideWinOrLose(
            Score dealerScore,
            Outcomes outcomes
    ) {
        getPlayer()
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> addPlayerOutcome(player, outcomes, decideOutcomType(player, dealerScore)));
    }

    private OutcomeType decideOutcomType(Player player, Score dealerScore) {
        return OutcomeType.decideWinOrLose(player.getCards().calcurateScore(), dealerScore);
    }

    private void addPlayerOutcome(Player player, Outcomes outcomes, OutcomeType outcomeType) {
        outcomes.addPlayerOutcomes(
                player.getName(),
                player.getBettingMoney(),
                outcomeType);
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
                .collect(Collectors.joining(String.valueOf(COMMA)));
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
                .filter(player -> player.calcurateScore().isBlackJack())
                .count();
    }

    // TODO : UI 로직 옮기기...!
    public void printBeginningUserCard() {
        users.forEach(User::printUserState);
    }

    public void printFinalOutput() {
        users.forEach(User::printFinalOutput);
    }
}
