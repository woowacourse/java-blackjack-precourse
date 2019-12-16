package woowacourse.blackjack;

import java.util.*;

import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;
import domain.card.CardFactory;
import domain.card.Card;

public class BlackJackGame {
    private Scanner sc = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private List<Card> cards =  CardFactory.create();
    private List<Card> getCardsList = new ArrayList<>();

    public void runGame() {
        List<String> playerNames = this.getPlayerNames();
        List<Double> bettingMoney = this.getBettingMoney(playerNames);
        this.setPlayerInformation(playerNames, bettingMoney);
        this.receiveTwice();
        System.out.println(this.dealer.getCards());
        for (Player player : this.players) {
            System.out.println(player.getCards());
        }
    }

    private List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(sc.nextLine().split(","));
    }

    private List<Double> getBettingMoney(List<String> playerNames) {
        System.out.println();
        List<Double> bettingMoney = new ArrayList<>();
        for (String playerName : playerNames) {
            System.out.println("" + playerName + "의 배팅 금액은?");
            bettingMoney.add(sc.nextDouble());
            System.out.println();
        }
        return bettingMoney;
    }

    private void setPlayerInformation(List<String> playerNames, List<Double> bettingMoney) {
        int amount = playerNames.size();
        for (int i = 0; i < amount; i++) {
            this.players.add(new Player(playerNames.get(i), bettingMoney.get(i)));
        }
    }

    private void receiveTwice() {
        for (int i = 0; i < 2; i++) {
            receiveOnceToPlayers();
            receiveOnceToDealer();
        }
    }

    private void receiveOnceToDealer() {
        this.dealer.addCard(getRandomCard());
    }

    private void receiveOnceToPlayers() {
        for (Player player: this.players) {
            this.handCardsToPlayer(player);
        }
    }

    private void handCardsToPlayer(Player player) {
        player.addCard(this.getRandomCard());
    }

    private Card getRandomCard() {
        Random random = new Random();
        Card getCard = null;
        boolean checkCard = true;
        while (checkCard) {
            getCard = this.cards.get(random.nextInt(this.cards.size()));
            checkCard = doubleCheck(getCard);
        }
        this.getCardsList.add(getCard);
        return getCard;
    }

    private boolean doubleCheck(Card getCard) {
        return this.getCardsList.contains(getCard);
    }
}
