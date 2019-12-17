package domain.user;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 게임 딜러를 의미하는 객체
 */

public class Dealer extends GameParticipant{
    private final static int NUMBER_OF_CARD_GIVEN_FIRST = 2;
    private final List<Card> cardpack = new CardFactory().create();

    public Dealer() {
        super("dealer");
    }

    public void giveInitCard(GameParticipant participant){
        for(int i=0; i < NUMBER_OF_CARD_GIVEN_FIRST; i++){
            giveCard(participant);
        }
    }

   private void giveCard(GameParticipant participant){
        Card pickedCard = pickOneOfCardInCardpack();
        participant.addCard(pickedCard);
   }

   private Card pickOneOfCardInCardpack(){
       Random random = new Random();
       Card pickedCard = cardpack.get(random.nextInt(cardpack.size()));
       cardpack.remove(pickedCard);
       return pickedCard;
   }

}
