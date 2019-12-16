package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Gamer 테스트")
class GamerTest {
    @Test
    @DisplayName("상속에 따라 Dealer와 Player 잘 생성되는지 확인")
    void createGamer() {
        Gamer player = new Player("mj", 1000);
        Gamer dealer = new Dealer();

        assertThat(player).isInstanceOf(Player.class);
        assertThat(player).isInstanceOf(Gamer.class);
        assertThat(dealer).isInstanceOf(Dealer.class);
        assertThat(dealer).isInstanceOf(Gamer.class);

    }

    @Test
    @DisplayName("Dealer와 Player 네임 구별 확인")
    void getGamerName() {
        Gamer player = new Player("mj", 1000);
        Gamer dealer = new Dealer();

        assertThat(player.getName()).isEqualTo("mj");
        assertThat(dealer.getName()).isEqualTo("dealer");
    }
}