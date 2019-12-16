package domain;

import static io.BlackJackGame.ZERO;

public class Name {

    private static final String BLANK = " ";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private static void validateName(String name) {
        if (name.length() == ZERO || name.contains(BLANK)) {
            throw new IllegalArgumentException(name + "은 올바르지 않은 이름입니다.");
        }
    }

    public String getName() {
        return name;
    }
}
