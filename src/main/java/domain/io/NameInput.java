package domain.io;

import domain.io.Message;

import java.util.Scanner;

public class NameInput {
    Message message = new Message();

    public String[] inputName() {
	String tmpname;
	Scanner sc = new Scanner(System.in);
	message.showNameInputMessage();
	tmpname = sc.nextLine();
	while (!checkName(tmpname)) {
	    message.showNameInputErrorMessage();
	    tmpname = sc.nextLine();
	}
	sc.close();
	return splitName(tmpname);
    }

    public boolean checkName(String name) {
	String[] tmpname = splitName(name);
	int i = 0;
	while (checkNamelength(tmpname[i]) && i < tmpname.length) {
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