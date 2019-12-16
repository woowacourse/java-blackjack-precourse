package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private boolean isUsedCard = false;
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public int getCardScore(){
        return symbol.getScore();
    }

    public void use(){
        isUsedCard = true;
    }
    public boolean isUsed(){
        return isUsedCard;
    }

    public boolean isACE(){
        if(symbol == Symbol.ACE){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return  symbol.getScore() + " " + type;
    }
}
