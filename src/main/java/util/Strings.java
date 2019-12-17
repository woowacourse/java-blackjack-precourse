package util;

import java.util.StringTokenizer;

public class Strings {
    public static StringTokenizer splitPlayerName(String names, String delimiter) {
        return new StringTokenizer(names, delimiter);
    }
}
