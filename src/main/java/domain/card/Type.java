package domain.card;

public enum Type {
    SPADE{
    	@Override
    	public String toString() {return "스페이드";}
    },
    DIAMOND{
    	@Override
    	public String toString() {return "다이아몬드";}
    },
    HEART{
    	@Override
    	public String toString() {return "하트";}
    },
    CLUB{
    	@Override
    	public String toString() {return "클로버";}
    }
}
