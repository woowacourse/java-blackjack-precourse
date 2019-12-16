import java.io.IOException;

import domain.controller.GameController;

/**
 * @author KIMSIYOUNG
 * @apiNote 게임시작을 위한 메인클래스로, 간단한 예외처리 이후 게임컨트롤러에게 게임진행을 요청합니다.
 * @since 2019-12-12
 */
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
