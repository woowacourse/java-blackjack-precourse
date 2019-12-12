package utils;

import java.util.Arrays;
import java.util.List;

public class UtilityMethods {
	public static List<String> sliceStringToList(String target) {
		List<String> splitTarget = Arrays.asList(target.split(","));
		return splitTarget;
	}
}