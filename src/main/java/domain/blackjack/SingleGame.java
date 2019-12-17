package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class SingleGame {
    private List<Card> cardDeck;
    private List<Player> playerList;

    public SingleGame() {
        CardFactory cardFactory = new CardFactory();
        this.cardDeck = cardFactory.shuffleCard();
        playerList = new ArrayList<>();
    }

    public void runSingleGame() {
        Dealer dealer = new Dealer();
        UserIO userIo = new UserIO();

        List<String> playerNameList = userIo.getName();
        for (String it : playerNameList) {
            int betAmount = userIo.getBetAmount(it);
            Player player = new Player(it, betAmount);    //Player 이름, 베팅액 입력
            player.addCard(distributeCard());             //두 장씩 카드 분배
            playerList.add(player);
        }

        dealer.addCard(distributeCard());                //딜러에게 두 장씩

        //플레이어 21 도달 확인
        //딜러 21 도달 확인

        //각각에게 추가 여부 묻고
        //딜러 16 이하면 카드 추가

        //점수 확인
        //턴 마칠 때마다 딜러와 플레이어들이 들고 있는 카드의 합 출력

        //종료 여부 확인

        //게임 종료 후 딜러와 플레이어의 최종 수익 비교
    }

    private List distributeCard() { //두 장 반환하고, cardDeck에서 두 장 삭제하고
        final int howMany = 2;
        List<Card> returnCard = cardDeck.subList(0, howMany);
        for (int i = 0; i < howMany; i++)
            cardDeck.remove(0);
        return returnCard;
    }

    private void judgeFirstDistribution(Dealer dealer) {
        if (dealer.isBlackJack()) {
            for (Player it : playerList) {
                if (!it.isBlackJack()) {
                    //P가 False이면 베팅액 손실
                    it.updateBalanceAfterLose(dealer);
                }
            }
            return;
        }

        for (Player it : playerList) {
            if (it.isBlackJack()) {
                //P가 True이면 베팅액 x 1.5 이득 return;
                continue;
            }
            //P가 False면 다음턴으로 return;
        }
    }
}
