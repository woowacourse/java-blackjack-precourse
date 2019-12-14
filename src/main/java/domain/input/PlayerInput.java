package domain.input;


import domain.tools.ToolBox;

import java.util.List;
import java.util.Scanner;

public class PlayerInput {
    Scanner scanner;

    public PlayerInput() {
        scanner = new Scanner(System.in);
    }

    public List<String> inputPlayerNames() {
        ToolBox tool = new ToolBox();
        return tool.splitPlayerName(scanner.nextLine());
    }

}
