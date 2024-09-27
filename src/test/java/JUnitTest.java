import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다") // 테스트 이름
    @Test // 테스트 메서드, 메서드를 호출할 때마다 새 인스턴스를 생성, 독립 테스트 가능
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        // Assertions, 예상 결과를 검증하는 assert~ 메서드 제공
        // Assertions.assertEquals(sum, a + b);
        assertThat(a + b).isEqualTo(sum);
    }

    @DisplayName("1 + 3는 3이다") // 테스트 이름
    @Test // 테스트 메서드, 메서드를 호출할 때마다 새 인스턴스를 생성, 독립 테스트 가능
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        // Assertions, 예상 결과를 검증하는 assert~ 메서드 제공
        // Assertions.assertEquals(sum, a + b);
        assertThat(a + b).isEqualTo(sum);
    }
}
