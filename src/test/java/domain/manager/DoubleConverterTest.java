package domain.manager;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleConverterTest {
    @Test
    public void convertTest() {
        DoubleConverter doubleConverter = new DoubleConverter();
        assertThat(doubleConverter.stringToDouble("1.2")).isEqualTo(1.2);
        assertThat(doubleConverter.stringToDouble("5")).isEqualTo((double) 5);
        assertThat(doubleConverter.stringToDouble("-0.4")).isEqualTo(-0.4);
        assertThat(doubleConverter.stringToDouble("asdf")).isEqualTo(0);
    }

}