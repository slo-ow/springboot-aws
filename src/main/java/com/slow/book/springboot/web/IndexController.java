package com.slow.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
        /*
            gradle 의 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정 된다.
            앞의 경로는 src/main/resources/templates 로 뒤의 파일 확장자는 .mustache 가 붙는 것이다. 즉 여기선 "index" 를
            반환 하므로, src/main/resources/templates/index.mustache 로 전환되어 View Resolver 가 처리하게 된다.
         */
    }
}
