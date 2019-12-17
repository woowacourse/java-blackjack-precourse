package domain.io;

import domain.io.Message;

import java.util.Scanner;

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
	ans = sc.nextLine();
	while (checkAns(ans)==false) {
	    message.showGettingCardErrorMessage();
	    ans=sc.nextLine();
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
