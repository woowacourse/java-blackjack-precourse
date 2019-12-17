package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {
	private static final int MAX_HITTABLE_SCORE = 16;
	
    public Dealer() {}
    
    public boolean isHittable() {
    	return calculateFinalScore() <= MAX_HITTABLE_SCORE;
    }
}
