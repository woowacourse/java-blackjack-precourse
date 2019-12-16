import java.io.IOException;

import domain.controller.GameController;

public class Application {
    public static void main(String[] args) throws IOException {
        try {
            GameController.playBlackjack();
        } catch (Exception e) {
            System.out.println("프로그램 상의 문제가 있는 것 같습니다. 담당자에게 연락 부탁드립니다. (010-3002-5691) \n");
            e.printStackTrace();
        }
    }
}
