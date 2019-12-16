package view;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static view.OutputView.showInputPlayerNamesInfo;
import static view.OutputView.showInputPlyerBettingMoneyInfo;

public class InputView {
    private static Scanner scanner;

    public InputView(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String getPlayerNames(){
        showInputPlayerNamesInfo();
        return scanner.nextLine();
    }


    public List<Double> getBettingMoneys(List<String> playerNames){
        return playerNames.stream()
                .map(name -> {
                    showInputPlyerBettingMoneyInfo(name);
                    return scanner.nextDouble();
                })
                .collect(Collectors.toList());

    }




}
