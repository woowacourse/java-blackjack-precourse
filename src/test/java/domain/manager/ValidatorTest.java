package domain.manager;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    @Test
    public void isContainsSpaceTest() {
        Validator validator = new Validator();
        List<String> example = new LinkedList<>();

        example.add("John");
        example.add("KIM");
        assertThat(validator.isContainsSpace(example)).isEqualTo(false);

        example.add("Pa rk");
        example.add("Hans");
        assertThat(validator.isContainsSpace(example)).isEqualTo(true);
    }

    @Test
    public void isBelowZeroTest() {
        Validator validator = new Validator();
        List<Double> example = new LinkedList<>();

        example.add((double) 50);
        example.add((double) 1.2);
        assertThat(validator.isBelowZero(example)).isEqualTo(false);

        example.add((double) -1);
        assertThat(validator.isBelowZero(example)).isEqualTo(true);
    }
}