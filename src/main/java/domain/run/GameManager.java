package domain.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class GameManager {

	final Scanner sc=new Scanner(System.in);
	ArrayList<Player> players=new ArrayList<Player>();
	
	public void printInputName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names=sc.next();
		splitName(names);
	}

	private void splitName(String names) {
		for(String name : names.split(",")) {
			System.out.println(name+"의 배팅금액은?");
			players.add(new Player(name,sc.nextDouble()));
		}
	}


}
