package com.slow.book.springboot.web;

import com.slow.book.springboot.service.posts.PostsService;
import com.slow.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // 1. 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다, postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달한다.
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
        /*
            gradle 의 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정 된다.
            앞의 경로는 src/main/resources/templates 로 뒤의 파일 확장자는 .mustache 가 붙는 것이다. 즉 여기선 "index" 를
            반환 하므로, src/main/resources/templates/index.mustache 로 전환되어 View Resolver 가 처리하게 된다.
         */
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts",dto);

        return "posts-update";
    }

}
