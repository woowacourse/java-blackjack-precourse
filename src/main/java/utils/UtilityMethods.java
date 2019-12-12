package utils;

import java.util.Arrays;
import java.util.List;

public class UtilityMethods {
	public static List<String> sliceStringToList(String target) {
		List<String> splitTarget = Arrays.asList(target.split(","));
		return splitTarget;
	}

	public static List<Double> addDoubleToList(List<Double> targetList, double targetDouble) {
		targetList.add(targetDouble);
		return targetList;
	}
}