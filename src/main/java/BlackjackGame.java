
import domain.input.InputName;
import java.util.List;

public class BlackjackGame {
    public static void main(String[] args) {
        List<String> a;
        InputName input = new InputName();
        a = input.inputName();
        System.out.println(a);
    }
}
