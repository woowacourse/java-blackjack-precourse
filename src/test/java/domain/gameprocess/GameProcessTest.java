package domain.gameprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GameProcessTest {
    @Test
    void create() {
        new GameProcess().play();
    }
}
