package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.InputView;

public class InputController {
	private static InputController inputController;
	private static final String ERROR_MESSAGE = "유효하지 않은 입력입니다.";
	private static final int NAME_MIN_LEN = 1;
	private static String YES_NO[] = {"y", "n"};
	private InputView inputView;

	private InputController() {
		inputView = new InputView();
	}

	public static InputController getInputController() {
		if (inputController == null) {
			inputController = new InputController();
		}
		return inputController;
	}

	public List<String> getPlayerNames() {
		List<String> playerNameList;
		try {
			playerNameList = inputView.getNameList();
			checkValidNameList(playerNameList);
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
			return getPlayerNames();
		}
		return playerNameList;
	}

	public List<Double> getBettings(List<String> names) {
		List<Double> bettingList = new ArrayList<>();
		for (String name : names
		) {
			bettingList.add(getBetting(name));
		}
		return bettingList;
	}

	private double getBetting(String name) {
		double bettingMoney;
		try {
			bettingMoney = inputView.getBettingMoney(name);
			checkValidBetting(bettingMoney);
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
			return getBetting(name);
		}
		return bettingMoney;
	}

	public boolean getYesOrNo(String name) {
		String draw;
		try {
			draw = inputView.getDrawYesOrNo(name, YES_NO);
			checkValidDraw(draw);
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
			return getYesOrNo(name);
		}
		return draw.equals(YES_NO[0]);
	}

	private void checkValidNameList(List<String> playerNameList) throws Exception {
		boolean checkResult = playerNameList.stream()
			.map(name -> isValidName(name))
			.reduce((validity1, validity2) -> validity1 & validity2)
			.get();
		if (checkResult == false) {
			throw new Exception();
		}
	}

	private void checkValidBetting(double bettingMoney) throws Exception {
		if (bettingMoney <= 0) {
			throw new Exception();
		}
	}

	private void checkValidDraw(String draw) throws Exception {
		if (Arrays.asList(YES_NO).contains(draw) == false) {
			throw new Exception();
		}
	}

	private boolean isValidName(String name) {
		return name.length() >= NAME_MIN_LEN;
	}
}
