package view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    @Test
    void getPlayerNames() {
        String playerNames = "mj,mj2,mj3";
        InputStream purchaseInputStream = new ByteArrayInputStream(playerNames.getBytes());
        InputView inputView = new InputView(purchaseInputStream);
        assertThat(inputView.getPlayerNames()).isEqualTo(playerNames);
    }

    @Test
    void getBettingMoneys() {
        List<String> playerNames = Arrays.asList("mj","mj2", "mj3");
        String bettingMoneys = "1000\n3000\n4000";
        InputStream purchaseInputStream = new ByteArrayInputStream(bettingMoneys.getBytes());
        InputView inputView = new InputView(purchaseInputStream);
        assertThat(inputView.getBettingMoneys(playerNames)).containsExactly(1000.0,3000.0,4000.0);
    }
}