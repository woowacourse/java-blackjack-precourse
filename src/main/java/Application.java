import java.util.Arrays;

import ui.Input;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        System.out.println(Arrays.toString(input.getNames()));
        System.out.println(input.getMoney("Mika"));
        System.out.println(input.wantsMoreCard("Mika"));
    }
}
