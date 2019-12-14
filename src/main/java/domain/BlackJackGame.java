package domain;

import domain.io.Message;
import domain.io.MoneyInput;
import domain.io.NameInput;

import domain.user.Player;
import domain.user.Dealer;

public class BlackJackGame {
    Message message = new Message();
    MoneyInput moneyinput = new MoneyInput();
    NameInput nameinput = new NameInput();
    private Player[] player;

    public void setPlayer() {
	String[] name = nameinput.inputName();
	for (int i = 0; i < name.length; i++) {
	    player[i] = new Player(name[i], moneyinput.inputMoney(name[i]));
	}
    }

}
