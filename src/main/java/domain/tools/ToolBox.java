package domain.tools;

import java.util.Arrays;
import java.util.List;

public class ToolBox {
    public List<String> splitPlayerName(String playerName) {
        return Arrays.asList(playerName.split(","));
    }



}
