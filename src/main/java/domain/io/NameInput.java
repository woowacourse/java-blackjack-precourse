package domain.io;

import domain.io.Message;

import java.util.Scanner;
import java.util.Arrays;

public class NameInput {
    Message message = new Message();

    public String[] inputName() {
	String tmpname;
	Scanner sc = new Scanner(System.in);
	message.showNameInputMessage();
	tmpname = sc.nextLine();
	tmpname=tmpname.replaceAll(" ", "");
	while (!checkName(tmpname)) {
	    message.showNameInputErrorMessage();
	    tmpname=tmpname.replaceAll(" ", "");
	    tmpname = sc.nextLine();
	}
	return splitName(tmpname);
    }

    public boolean checkName(String name) {
	String[] tmpname = splitName(name);
	int i = 0;
	while (i < tmpname.length && checkNamelength(tmpname[i])) {
	    i++;
	}
	if (i == tmpname.length) {
	    return true;
	}
	return false;
    }

    public String[] splitName(String name) {
	return name.split(",");
    }

    public boolean checkNamelength(String name) {
	if (name.length() == 0) {
	    return false;
	}
	return true;
    }
}