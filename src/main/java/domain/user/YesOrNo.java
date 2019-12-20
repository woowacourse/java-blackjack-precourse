package domain.user;

import java.util.Objects;

/**
 * y(Y), n(N) 을 구분하기 위한 객체
 */
public class YesOrNo {
    private final char value;

    public YesOrNo(String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("값이 null 값이 될 수 없습니다.");
        }
        if (value.length() != 1) {
            throw new IllegalArgumentException("입력 문자는 한 글자여야 합니다.");
        }

        this.value = Character.toLowerCase(value.charAt(0));
        if (this.value != 'y'  && this.value != 'n') {
            throw new IllegalArgumentException("입력 문자는 y(Y), n(N)만 가능합니다.");
        }
    }

    public boolean isYes() {
        return value == 'y';
    }
}
