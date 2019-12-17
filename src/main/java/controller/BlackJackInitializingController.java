package controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import domain.user.Status;

import java.util.ArrayList;
import java.util.List;


/**
 * 플레이어, 딜러, 카드 초기화 클래스
 */
public class BlackJackInitializingController {
	public static List<Player> initializePlayers(List<String> names, List<Double> bettings) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(names.get(i), bettings.get(i)));
		}
		return players;
	}

	public static List<Card> initializeCardDeck() {
		List<Card> cardDeck = CardFactory.create();
		return cardDeck;
	}

	public static List<Integer> initializeCardIndexList(int index) {
		List<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < index; i++) {
			indexList.add(i);
		}
		return indexList;
	}

	public static List<Status> initializePlayersStatus(int index) {
		List<Status> playersStatus = new ArrayList<Status>();
		for (int i = 0; i < index; i++) {
			playersStatus.add(Status.KEEP_GO);
		}
		return playersStatus;
	}
}
