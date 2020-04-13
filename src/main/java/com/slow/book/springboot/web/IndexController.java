package com.slow.book.springboot.web;

import com.slow.book.springboot.config.auth.LoginUser;
import com.slow.book.springboot.config.auth.dto.SessionUser;
import com.slow.book.springboot.service.posts.PostsService;
import com.slow.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 1. 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다, postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달한다.
        // *추가. 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨. 이제는 @LoginUser 만 사용하면 세션 정보를 가져올 수 있게 됨.
        model.addAttribute("posts",postsService.findAllDesc());
        // @LoginUser 를 사용하기전에 세션을 가져오던 방식 SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
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
