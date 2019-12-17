package domain.io;

import domain.io.Message;

import java.util.Scanner;


/**
 * 추가 카드를 받을지 안 받을지 판단하는 역할을 하는 클래스
 */
public class MoreCardInput {
    Message message = new Message();
    Scanner sc = new Scanner(System.in);
    private final static String CAPITALYES = "Y";
    private final static String SMALLYES = "y";
    private final static String CAPITALNO = "N";
    private final static String SMALLNO = "n";
    
    public boolean getMoreCardOrNot(String name) {
	String ans;
	message.showGettingCardQuestion(name);
	while (checkAns(ans=sc.nextLine())==false) {
	    message.showGettingCardErrorMessage();
	}
	if(ans.equals(CAPITALYES)||(ans.equals(SMALLYES))) {
	    return true;
	}
	return false;
    }

    public boolean checkAns(String a) {
	a=a.replaceAll(" ", "");
	if (a.equals(CAPITALYES)|| a.equals(SMALLYES)|| a.equals(CAPITALNO) || a.equals(SMALLNO)) {
	    return true;
	}
	return false;
    }
}
