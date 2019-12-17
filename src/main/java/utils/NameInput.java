package utils;

/**
 * 게임 참여 인원의 이름을 받는 객체
 */
public class NameInput {
    private static final int MIN_LENGTH = 0;
    private static final int MAX_GAMERS_LENGTH = 7;
    private String[] names;

    public NameInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        input();
    }
    

    public String[] getNames() {
        return this.names;
    }

    private void input() {
        String[] output = UserInput.input().split(",", -1);

        try {
            validateNamesLength(output);
            validateLength(output);
            this.names = output;
        } catch (IllegalArgumentException e) {
            input();
        }
    }

    private void validateNamesLength(String[] strings) {
        for (String string : strings) {
            validateNameLength(string);
        }
    }

    private void validateNameLength(String string) {
        if (isMinLength(string)) {
            System.out.println("이름을 " 
                    + MIN_LENGTH 
                    + "자 보다 많이 입력하세요.");
            throw new IllegalArgumentException();
        }
    }

    private void validateLength(String[] strings) {
        if (isMoreMax(strings)) {
            System.out.println("최대 인원 수는 " 
                    + MAX_GAMERS_LENGTH 
                    + "명입니다. 다시 입력하세요.");
            throw new IllegalArgumentException();
        }
    }
    
    private boolean isMinLength(String string) {
        return string.length() <= MIN_LENGTH;
    }
    
    private boolean isMoreMax(String[] strings) {
        return strings.length > MAX_GAMERS_LENGTH;
    }
}
