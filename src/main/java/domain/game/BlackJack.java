package domain.game;

public class BlackJack {
    /**
     * 이 클래스는 싱글톤 기법으로 설계되었다.
     * 프로그램 설계 상 여러 블랙잭 게임이 동시에 실행될 수 없으므로,
     * 하나의 블랙잭 게임을 static으로 생성해 두고 이를 활용하게 설계하였다.
     * 본 프로젝트에는 동기화 이슈가 없지만, 추후 재사용하거나 참고할 떄를 대비하여
     * 별도의 Holder 클래스를 둬 멀티쓰레딩 환경에서 여러 개가 생성되지 않도록 하였다.
     */
    private BlackJack() {
    }

    private static class BlackJackHolder {
        public static final BlackJack INSTANCE = new BlackJack();
    }

    public static BlackJack getInstance(){
        return BlackJackHolder.INSTANCE;
    }

    public void playGame() {

    }
}
