package com.slow.book.springboot.web.dto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
// 0-1. Junit 의 assertThat 이 아닌 assertj 의 assertThat 을 사용 한 이유
// 0-2. Junit 의 assertThat 을 쓰게 되면 is() 와 같이 CoreMatchers 라이브러리가 필요하다. = 결론적으로 추가적인 라이브러리가 필요 하지 않기 때문에 assertj 채택
// 0-3. IDE 에서는 CoreMatchers 와 같은 Matcher 라이브러리의 자동완성 지원이 약하다. = 결론적으로 자동완성이 좀 더 확실하게 지원되기때문에 assertj 채택
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // 1. assertThat (assertj 라는 테스트 검증 라이브러리의 검증 메소드)
        assertThat(dto.getAmount()).isEqualTo(amount); // 2. isEqualTo (assertj 의 동등 비교 메소드, assertThat 에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공)
    }
}
