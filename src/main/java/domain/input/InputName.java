/*
 * class: InputName
 *
 * version: 1.0v
 *
 * date: 2019.12.15
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
    List<String> name;
    Scanner sc = new Scanner(System.in);
    String stringName;

    /**
     * 플레이어 이름을 개행문자를 기준으로 받고 ','구분자로 파싱함
     *
     * @return 플레이어의 이름을 담고있는 list
     */
    public List<String> inputName() {
        stringName = sc.nextLine();
        name = Arrays.asList(stringName.split(","));

        return name;
    }
}
