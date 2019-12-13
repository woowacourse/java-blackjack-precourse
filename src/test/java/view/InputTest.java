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
    public void containsDoubleCommaTest() {
        Input input = new Input();
        assertThat(input.containsDoubleComma("자바,안드,아이,,냄비,마스")).isTrue();
        assertThat(input.containsDoubleComma("자바,안드,아이,냄비,마스")).isFalse();
    }

    @Test
    public void endWithCommaTest() {
        Input input = new Input();
        assertThat(input.endWithComma("자바,")).isTrue();
        assertThat(input.endWithComma("자바,코틀")).isFalse();
    }

    @Test
    public void splitAsCommaTest() {
        Input input = new Input();
        assertThat(input.splitAsComma("가나,다라,마바").toString())
                .isEqualTo("[가나, 다라, 마바]");
    }
}