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
		List<Double> bettings = new ArrayList<Double>();
		for (int i = 0; i < userInput.size(); i++) {
			System.out.println("Bettings");
			double betting = sc.nextDouble();
			bettings.add(betting);
		}
		System.out.println(userInput);
		System.out.println("Bettings");
		List<Player> players = BlackJackInitializingSource
			.initializePlayers(userInput, bettings);
		for (Player player : players) {
			player.inputTest();
		}

	}
}
