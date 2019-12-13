/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package util;

import java.util.Random;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 카드를 뽑을 때에 필요한 임의의 정수를 뽑는 클래스입니다.
 * @apiNote 0부터 남아있는 카드리스트의 최대 인덱스만큼의 정수를 뽑습니다.
 * @since : 2019.12.13 금요일
 */
public class RandomIntegerGenerator {
    private static int randomInt;

    public static int generateRandomIntegerByInputInteger(int maxInt) {
        randomInt = new Random().nextInt(maxInt);
        return randomInt;
    }

}
