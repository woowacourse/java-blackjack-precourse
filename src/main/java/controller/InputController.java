package controller;

import java.util.List;

import view.InputView;

public class InputController {
	private static String ERROR_MESSAGE = "유효하지 않은 입력입니다.";
	private static final int NAME_MIN_LEN = 1;
	InputView inputView;

	public List<String> getPlayerNameList() {
		List<String> playerNameList;
		try {
			playerNameList = inputView.getNameList();
			checkValidNameList(playerNameList);
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
			return getPlayerNameList();
		}
		return playerNameList;
	}

	public double getPlayerBettingMoney(String name) {
		double bettingMoney;
		try {
			bettingMoney = inputView.getBettingMoney(name);
			checkValidBettingMoney(bettingMoney);
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
			return getPlayerBettingMoney(name);
		}
		return bettingMoney;
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

	private void checkValidBettingMoney(double bettingMoney) throws Exception {
		if (bettingMoney <= 0) {
			throw new Exception();
		}
	}

	private boolean isValidName(String name) {
		return name.length() >= NAME_MIN_LEN;
	}
}
