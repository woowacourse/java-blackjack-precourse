package domain.card;

import java.util.ArrayList;

/**
 * AceCardSum
 * 버전 : 1.0
 * Ace카드 경우의 수
 */
public class AceCardSum {
	private final static int ONE_ACE_MIN = 2;
	private final static int ONE_ACE_MAX = 12;
	private final static int TWO_ACE_MIN = 3;
	private final static int TWO_ACE_MAX = 13;
	private final static int THREE_ACE_MIN = 4;
	private final static int THREE_ACE_MAX = 14;

	public static ArrayList<ArrayList<Integer>> createAceSumList() {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(createOneAce());
		list.add(createTwoAce());
		list.add(createThreeAce());
		return list;
	}

	public static ArrayList<Integer> createOneAce() {
		ArrayList<Integer> element = new ArrayList<Integer>();
		element.add(ONE_ACE_MIN);
		element.add(ONE_ACE_MAX);
		return element;
	}

	public static ArrayList<Integer> createTwoAce() {
		ArrayList<Integer> element = new ArrayList<Integer>();
		element.add(TWO_ACE_MIN);
		element.add(TWO_ACE_MAX);
		return element;
	}

	public static ArrayList<Integer> createThreeAce() {
		ArrayList<Integer> element = new ArrayList<Integer>();
		element.add(THREE_ACE_MIN);
		element.add(THREE_ACE_MAX);
		return element;
	}
}
