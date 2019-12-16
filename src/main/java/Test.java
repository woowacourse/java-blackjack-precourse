/*
* test clas
* */
import domain.input.InputName;
import domain.input.InputMoney;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> a;
        InputName input = new InputName();
        InputMoney money = new InputMoney();
        a = input.inputName();
        System.out.println(a);

        HashMap<String, Double> maps = money.inputMoney(a);
        maps.forEach((key,value)->{
            System.out.println(key);
            System.out.println(value);
        });
    }

}
