package domain.io;

import domain.io.Message;

import java.util.Scanner;

public class MoreCardInput {
    Message message = new Message();
    Scanner sc = new Scanner(System.in);
    
    public boolean getMoreCardOrNot(String name) {
	String ans;
	message.showGettingCardQuestion(name);
	while (!checkAns(ans=sc.nextLine())) {
	    message.showGettingCardErrorMessage();
	}
	if(ans=="y"||ans=="Y") {
	    return true;
	}
	return false;
    }

    public boolean checkAns(String a) {
	a=a.replaceAll(" ", "");
	if (a == "y" || a == "n" || a == "Y" || a == "N") {
	    return true;
	}
	return false;
    }
}
