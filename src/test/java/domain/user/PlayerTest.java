package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Player 테스트")
class PlayerTest {
    @Test
    @DisplayName("Player생성 테스트")
    void name() {
        Player player = new Player("mj", 10000 );
        assertThat(player.getBettingMoney()).isEqualTo(10000);
        assertThat(player.getName()).isEqualTo("mj");

    }
}