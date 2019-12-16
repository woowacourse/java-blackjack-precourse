/*
 * class: InputMoney
 *
 * version: 1.0v
 *
 * date: 2019.12.16
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.16
 */

package domain.input;

import java.util.InputMismatchException;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 플레이어의 배팅금액을 입력하는 기능
 *
 * @author joseph415
 * @version 1.0 2019.12.16
 */
public class InputMoney {
    int index = 0;
    Double money;
    Scanner sc = new Scanner(System.in);
    HashMap<String, Double> relations = new HashMap<>();

    /**
     * 배팅금액을 입력을 실행하는 메소드
     * @param   names 사용자의 이름List를 메개변수로 받는다.
     * @return  배팅금액과 사용자 이름을 묶은 hashmap을 리턴
     */
    public HashMap<String, Double> inputMoney(List<String> names) {
        while (index < names.size()) {
            System.out.println(names.get(index) + "의 배팅금액은?");
            exceptIOManagement(names);
        }
        return relations;
    }

    /**
     * 배팅금액으로 숫자 이외의 값을 input 했을 때 예외처리
     * 예외가 안나면 realations 에 입력후 exceptMoney메소드로 금액이 정상입력인지 다시 판단.
     * @param names 플레이어의 이름리스트
     */
    public void exceptIOManagement(List<String> names) {
        try {
            money = sc.nextDouble();
            relations.put(names.get(index), money);
            exceptMoney();
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("숫자만 입력!! 재입력");
            sc.nextLine();
        }
    }

    /**
     * 예외처리를 하는 메소드 배팅금액은 양수만 입력받는다.
     * 정상입력할경우만 index를 올린다.
     * 예외일경우 index를 올리지않고 이전에 썼던 잘못된 값을 덮어씌운다.
     */
    public void exceptMoney() {
        if (money > 0) {
            index++;
            return;
        }
        System.out.println("배팅금액은 오류 1원이상입력 !!재입력");
    }
}

