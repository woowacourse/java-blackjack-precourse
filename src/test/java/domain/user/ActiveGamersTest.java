package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("참여하는 Gamer 객체 리스트 생성")
class ActiveGamersTest {

    @DisplayName("입력받은 이름과 베팅 리스트를 매핑하여 참여하는 Gamer객체 생성")
    @Test
    public void createActiveGamers(){
        List<String> playerNames = Arrays.asList("p1", "p2");
        List<Double> playerBettings = Arrays.asList(1000.0, 2000.0);
        ActiveGamers activeGamers = new ActiveGamers(playerNames, playerBettings);
        assertThat(activeGamers.getPlayers().size()).isEqualTo(2);

        assertThat(activeGamers.getPlayers().get(0).getName()).isEqualTo("p1");
        assertThat(activeGamers.getPlayers().get(0).getBettingMoney()).isEqualTo(1000.0);

        assertThat(activeGamers.getDealer()).isNotNull();
    }

    @DisplayName("참여하는 Gamer객체 생성시 들어온 이름과 베팅 가격의 사이즈가 다른 경우 Exception")
    @Test
    public void exceptionTestInCreateActiveGamers(){
        List<String> playerNames = Arrays.asList("p1", "p2");
        List<Double> playerBettings = Arrays.asList(1000.0);


        assertThrows(
                IllegalArgumentException.class,
                ()->{ActiveGamers activeGamers = new ActiveGamers(playerNames, playerBettings);}
        );
    }

}