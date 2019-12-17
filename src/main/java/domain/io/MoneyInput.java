package domain.io;

import domain.io.Message;
import java.util.Scanner;


/**
 * 배팅금액을 입력받는 기능을 하는 클래스
 */
public class MoneyInput {
    Message message = new Message();

    public int inputMoney(String name) {
	String money;
	Scanner sc = new Scanner(System.in);
	message.showMoneyInputMessage(name);
	money = sc.nextLine();
	while (!checkMoney(money)) {
	    message.showMoneyInputErrorMessage();
	    money = sc.nextLine();
	}
	return Integer.parseInt(money);
    }

    public boolean checkMoney(String money) {
	if (!isStringInt(money)) {
	    return false;
	}
	if (Integer.parseInt(money) <= 0) {
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
