package ui;

import java.util.Scanner;

public class Input {

    // 테스트용
    private Scanner scanner = new Scanner("mika,john\n200\n500\nn\nn");

    // private Scanner scanner = new Scanner(System.in);

    public String[] getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine().split(",");
    }

    public double getMoney(String name) {
        System.out.println("\n" + name + "의 배팅 금액은?");
        return Double.parseDouble(scanner.nextLine());
    }

    public boolean wantsMoreCard(String name) {
        System.out.println("\n" + name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine().equals("y");
    }
}
