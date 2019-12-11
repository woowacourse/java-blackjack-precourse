package domain.user;

public class PlayerConstraints {
	private static final int MIN_MONEY = 1;
	
    public static void checkValidName(String name) {
    	checkEmptyName(name);
    }
    
    private static void checkEmptyName(String name) {
    	if (name.isEmpty()) {
    		throw new IllegalArgumentException("비어있지 않은 이름을 입력해주세요");
    	}
    }
    
    public static void checkValidMoney(int money) {
    	checkMoneyRange(money);
    }
    
    private static void checkMoneyRange(int money) {
    	if(money < MIN_MONEY) {
    		throw new IllegalArgumentException("1원 이상을 걸어주세요");
    	}
    }
}
