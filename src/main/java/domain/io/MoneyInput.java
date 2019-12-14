package domain.io;

import domain.io.Message;
import java.util.Scanner;

public class MoneyInput {
    Message message = new Message();

    public int inputMoney(String name) {
	Scanner sc=new Scanner(System.in);
	String money;
	message.showMoneyInputMessage(name);
	money=sc.nextLine();
	while(!checkMoney(money)) {
	    message.showMoneyInputErrorMessage();
	    money=sc.nextLine();
	}
	return Integer.parseInt(money);
    }

    public boolean checkMoney(String money) {
	if (!isStringInt(money)) {
	    return false;
	}
	if(Integer.parseInt(money)<=0) {
	    return false;
	}
	return true;

    }

    public boolean isStringInt(String n) {
	try {
	    Integer.parseInt(n);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }
}
