package view;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputTest {
    @Test
    public void inputInitTest() {
        Input input = new Input();
        assertThat(input).isNotNull();
    }

    @Test
    public void notContainsDoubleCommaTest() {
        Input input = new Input();
        assertThat(input.notContainsDoubleComma("자바,안드,아이,,냄비,마스")).isFalse();
        assertThat(input.notContainsDoubleComma("자바,안드,아이,냄비,마스")).isTrue();
    }

    @Test
    public void notEndWithCommaTest() {
        Input input = new Input();
        assertThat(input.notEndWithComma("자바,")).isFalse();
        assertThat(input.notEndWithComma("자바,코틀")).isTrue();
    }

    @Test
    public void splitAsCommaTest() {
        Input input = new Input();
        assertThat(input.splitAsComma("가나,다라,마바").toString())
                .isEqualTo("[가나, 다라, 마바]");
    }
}