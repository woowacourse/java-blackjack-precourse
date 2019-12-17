package view;

import domain.user.Player;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static util.CustomErrorMessage.INVALID_ANSWER_TYPE;
import static view.OutputView.*;

public class InputView {
    private static Scanner scanner;

    public InputView(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String getPlayerNames(){
        showInputPlayerNamesInfo();
        return scanner.nextLine();
    }

    public List<Double> getBettingMoneys(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> {
                    showInputPlyerBettingMoneyInfo(name);
                    return scanner.nextDouble();
                })
                .collect(Collectors.toList());
    }

    public String getYesOrNo(Player player) {
        showGetOneMoreCardInfo(player);
        String next = scanner.next();
        try {
            validateAnswer(next.toLowerCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getYesOrNo(player);
        }
        return next;
    }

    private void validateAnswer(String answer) throws IllegalArgumentException {
        if (!answer.equals("y") && !answer.equals("n"))
            throw new IllegalArgumentException(INVALID_ANSWER_TYPE);
    }


}
