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
    public void hasOverlapTest() {
        Validator validator = new Validator();
        List<String> example = new LinkedList<>();

        example.add("John");
        example.add("KIM");
        assertThat(validator.hasOverlap(example)).isEqualTo(false);

        example.add("John");
        assertThat(validator.hasOverlap(example)).isEqualTo(true);
    }

    @Test
    public void isBelowZeroTest() {
        Validator validator = new Validator();

        assertThat(validator.isBelowZero((double) 1)).isEqualTo(false);
        assertThat(validator.isBelowZero(0.4)).isEqualTo(false);

        assertThat(validator.isBelowZero((double) 0)).isEqualTo(true);
        assertThat(validator.isBelowZero(-1.5)).isEqualTo(true);
    }
}