package domain.run;

import java.util.Scanner;

public class GameManager {

	final Scanner sc=new Scanner(System.in);
	
	public void printInputName() {
		System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
		String names=sc.next();
		splitName(names);
	}


}
