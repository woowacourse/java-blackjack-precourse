package domain.card;

public enum Type {
	/**
	 * 이름 변수를 추가해 소유한 카드를 출력할 때 이용한다.
	 */
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");
    
    private String name;
	
	Type(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
