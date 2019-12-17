package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author KIMSIYOUNG
 * @apiNote 테스트 클래스 설명을 위한 어노테이션 입니다.
 * @since 2019-12-16
 */
@Retention(RetentionPolicy.SOURCE)
public @interface TestDescription {
    String value();
}
