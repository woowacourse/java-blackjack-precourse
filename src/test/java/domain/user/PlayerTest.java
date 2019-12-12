package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    public void playerInitTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player).isNotNull();
    }

    @Test
    public void playerFieldTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player.getCards()).isNotNull();
    }
}