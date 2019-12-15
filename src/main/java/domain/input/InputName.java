/*
 * class: InputName
 *
 * version: 1.0v
 *
 * date: 2019.12.16
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.15
 */

package domain.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 플레이어의 이름을 생성하는 기능
 *
 * @author joseph415
 * @version 1.0 2019.12.15
 */
public class InputName {
    List<String> names;
    Scanner sc = new Scanner(System.in);
    String stringName;

    /**
     * 플레이어 이름을 개행문자를 기준으로 받고 ','구분자로 파싱함
     *
     * @return 플레이어의 이름을 담고있는 list
     */
    public List<String> inputName() {
        boolean exception = true;

        while (exception) {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            stringName = sc.nextLine();
            names = Arrays.asList(stringName.split(","));
            exception = exceptName();
        }
        return names;
    }

    /**
     * 공백이 입력될경우 또는 ,name와 같이 구분자만 나와서 빈스트링이 입력될경우 예외상황 true 리턴
     *
     * @return 예외상황이 발생할 경우 true 를 리턴한다.
     */
    public boolean exceptName() {
        if (names.stream().filter(name1 -> name1.contains(" ")).anyMatch(name1 -> name1.contains(" ")) ||
                names.contains("")) {
            System.out.println("이름 입력 오류 재입력!! 공백불가");
            return true;
        }
        return false;
    }
}
