package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static util.StringParsing.makeStringListFromString;

@DisplayName("인자의 스트링을 파싱하는 클래스")
class StringParsingTest {

    @Test
    @DisplayName("쉼표를 기준으로 List<String>생성, 이때 , 사이 공백도 지워버린다.")
    void makeStringListFromStringTest() {
        List<String> names = makeStringListFromString("mj,mj2, mj3, m4");
        assertThat(names.size()).isEqualTo(4);
        assertThat(names).containsExactly("mj","mj2","mj3","m4");
    }
}