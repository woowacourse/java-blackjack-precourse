package domain.manager;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    @Test
    public void isContainsSpace() {
        Validator validator = new Validator();
        List<String> example = new LinkedList<>();

        example.add("John");
        example.add("KIM");
        assertThat(validator.isNotContainsSpace(example)).isEqualTo(true);

        example.add("Pa rk");
        example.add("Hans");
        assertThat(validator.isNotContainsSpace(example)).isEqualTo(false);
    }
}