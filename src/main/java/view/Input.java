package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Input {
    private static final String COMMA = ",";

    private Scanner scanner = new Scanner(System.in);

    public Input() {
    }

    public String input() {
        return scanner.nextLine();
    }

    public List<String> asGamers() {
        return Collections.emptyList();
    }

    public boolean containsDoubleComma(String input) {
        return input.contains(COMMA + COMMA);
    }

    public boolean endWithComma(String input) {
        return input.endsWith(COMMA);
    }

    public List<String> splitAsComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }
}
