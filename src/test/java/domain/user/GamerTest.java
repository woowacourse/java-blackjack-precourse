package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import annotation.TestDescription;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

class GamerTest {
    @Test
    @TestDescription("생성된 유저의 카드가 21초과인 경우 버스트가 정상작동하는 지 테스트합니다.")
    public void burstTest(){
        Player player = new Player("piglet",30000);
        player.addCard(new Card(Symbol.JACK, Type.CLUB));
        player.addCard(new Card(Symbol.JACK, Type.CLUB));
        player.addCard(new Card(Symbol.TWO, Type.CLUB));
        assertTrue(player.isBurst());
    }

    @Test
    @TestDescription("유저가 처음 받은 2개의 합이 21인 경우 first blackjack 메서드가 정상작동하는지 테스트합니다.")
    public void firstBlackjackTest(){
        Player player = new Player("piglet",1000);
        player.addCard(new Card(Symbol.ACE,Type.CLUB));
        player.addCard(new Card(Symbol.JACK,Type.DIAMOND));
        assertTrue(player.isBlackJack());
    }

    @Test
    @TestDescription("유저가 에이스를 가지고 있는 경우 카드의 합을 정상적으로 계산하는지 테스트합니다.")
    public void sumOfCardTest(){
        Gamer gamer = new Gamer();
        gamer.addCard(new Card(Symbol.ACE,Type.CLUB));
        gamer.addCard(new Card(Symbol.TWO,Type.DIAMOND));
        gamer.addCard(new Card(Symbol.TEN,Type.DIAMOND));
        assertEquals(gamer.sumOfCard(), 13);  //  에이스를 제외한 합이 11을 넘는경우, 에이스를 1로계산하는지
    }

    @Test
    @TestDescription("유저가 에이스를 가지고 있는 경우 카드의 합을 정상적으로 계산하는지 테스트합니다.")
    public void sumOfCardTestLess11(){
        Gamer gamer = new Gamer();
        gamer.addCard(new Card(Symbol.ACE,Type.CLUB));
        gamer.addCard(new Card(Symbol.TEN,Type.DIAMOND));
        assertEquals(gamer.sumOfCard(), 21);  //  에이스를 제외한 합이 11보다 작은경우, 에이스를 11로계산하는지
    }

}