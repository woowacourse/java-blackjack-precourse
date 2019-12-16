package utils;

import java.util.Arrays;
import java.util.List;

public class UtilityMethods {
	public static List<String> sliceStringToList(String target) {
		List<String> splitTarget = Arrays.asList(target.split(","));
		return splitTarget;
	}

	public static int generateRandomNumber(int limit) {
		int randomNumber = (int) (Math.random() * limit);
		return randomNumber;
	}
}