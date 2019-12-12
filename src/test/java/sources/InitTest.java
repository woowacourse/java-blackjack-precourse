package sources;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;
import utils.UtilityMethods;

public class InitTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Names");
		List<String> userInput = UtilityMethods
			.sliceStringToList(sc.nextLine());
		System.out.println(userInput);
		System.out.println("Bettings");
		double betting = sc.nextDouble();
		List<Double> bettings = new ArrayList<Double>();
		bettings.add(betting);
		List<Player> players = BlackJackInitializingSource
			.initializePlayers(userInput, bettings);
		for (Player player : players) {
			player.inputTest();
		}

	}
}
