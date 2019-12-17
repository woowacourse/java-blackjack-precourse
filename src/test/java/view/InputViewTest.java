package view;

import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<String> playerNames = Arrays.asList("mj", "mj2", "mj3");
        String bettingMoneys = "1000\n3000\n4000";
        InputStream purchaseInputStream = new ByteArrayInputStream(bettingMoneys.getBytes());
        InputView inputView = new InputView(purchaseInputStream);
        assertThat(inputView.getBettingMoneys(playerNames)).containsExactly(1000.0, 3000.0, 4000.0);
    }


    @Test
    @DisplayName("y,n가 아닌 대답을 했을 경우 Exception일어나고 재 입력")
    void getYesOrNo() {
        Player player = new Player("mj", 1000);
        String answer = "d\ng\ny";
        InputStream purchaseInputStream = new ByteArrayInputStream(answer.getBytes());
        InputView inputView = new InputView(purchaseInputStream);
        assertThat(inputView.getYesOrNo(player)).isEqualTo("y");
    }


}