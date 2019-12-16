package domain;
/**
 * 기능을 최종적으로 호출하는 클래스
 */
public class Main {
    public static void main(String args[]) {
        BlackjackManager blackjackManager = new BlackjackManager();
        blackjackManager.initState();
        if (!blackjackManager.existsBlackjack())
            blackjackManager.playGame();
        blackjackManager.produceResult();
    }
}
