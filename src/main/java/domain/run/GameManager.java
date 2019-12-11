package domain.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class GameManager {

	final Scanner sc=new Scanner(System.in);
	ArrayList<Player> players=new ArrayList<Player>();
	
	public void printInputName() {
		System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
		String names=sc.next();
		splitName(names);
	}

	private void splitName(String names) {
		for(String name : names.split(",")) {
			System.out.println(name+"�� ���ñݾ���?");
			players.add(new Player(name,sc.nextDouble()));
		}
	}


}
