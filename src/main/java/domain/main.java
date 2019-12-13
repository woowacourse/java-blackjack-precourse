package domain;

import domain.input.playerInput;
import domain.tools.ToolBox;

public class main {

    public static void main(String[] args) {
        playerInput input = new playerInput();
        ToolBox toolbox = new ToolBox();


        System.out.println("플레이어 이름을 입력해주세요. (쉼표 기준으로 분리)");
        for(String i : toolbox.splitPlayerName(input.inputPlayerNames())) {
            System.out.println("Hello: " + i);
        }
    }

}
