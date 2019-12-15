package domain.gameprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameProcessTest {
    @Test
    void play() {
        String input = "yelim,helim";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        new GameProcess().play();
    }
}
