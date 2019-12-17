package view;

import static controller.InputController.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.user.BlackjackMember;

public class OutputView {
	private static final String FIRST_SERVE = "에게 2장을 나었습니다.";

	public static void printFirstServeMessage(List<BlackjackMember> blackjackMembers) {
		List<String> names = new ArrayList<>();
		for (BlackjackMember blackjackMember : blackjackMembers) {
			names.add(blackjackMember.getName());
		}

		System.out.println(String.join(COMMA, names) + FIRST_SERVE);
	}

	public static void printAllStatus(List<BlackjackMember> blackjackMembers) {
		for (BlackjackMember blackjackMember : blackjackMembers) {
			printMemberStatus(blackjackMember);
		}
	}

	public static void printMemberStatus(BlackjackMember blackjackMember) {
		System.out.print(blackjackMember.getName());
		System.out.println(deckToString(blackjackMember));
	}

	private static String deckToString(BlackjackMember blackjackMember) {
		List<String> deck = new ArrayList<>();
		for (Card card : blackjackMember.getDeck()) {
			deck.add(card.toString());
		}

		return String.join(COMMA, deck);
	}


}
