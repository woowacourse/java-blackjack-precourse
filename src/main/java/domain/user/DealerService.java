package domain.user;

public interface DealerService {
    void confirmCards(User user);

    void receiveDefaultCards(User user);

    void printResult(User user);

    void printProfit(User user);
}
