package util;

import static util.CustomErrorMessage.CHECKING_SIZE_ERROR;

public class Validation {
    public static void sizeEqualValidate(int checkingSize1, int checkingSize2) {
        if (checkingSize1 != checkingSize2)
            throw new IllegalArgumentException(CHECKING_SIZE_ERROR);
    }
}
