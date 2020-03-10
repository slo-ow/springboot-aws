package com.slow.book.springboot.web;
import com.slow.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1. 컨트롤러를 JSON 을 반환하는 컨트롤러로 만들어 준다. 예전에는 @ResponseBody 를 각 메소드마다 선언했던 것을 한번에 사용 할 수 있게 해줌.
public class HelloController {
    @GetMapping("/hello") // 2. HTTP Method 인 GET 의 요청을 받을 수 있는 API 를 만들어 준다.
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션
           @RequestParam("amount") int amount) { // 여기서는 외부에서 name(@RequestParam("name")) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 된다.
        return new HelloResponseDto(name, amount);
    }

}
