package domain.card;

public enum Type {
    SPADE{
    	@Override
    	public String toString() {return "�����̵�";}
    },
    DIAMOND{
    	@Override
    	public String toString() {return "���̾Ƹ��";}
    },
    HEART{
    	@Override
    	public String toString() {return "��Ʈ";}
    },
    CLUB{
    	@Override
    	public String toString() {return "Ŭ�ι�";}
    }
}
