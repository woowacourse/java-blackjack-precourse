package domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("클로버"),
    CLUB("클럽");
    
	private String suit;
	
    Type(String suit) {
    	this.suit = suit;
    }
    
    public String getSuit() {
        return this.suit;
    }
}
