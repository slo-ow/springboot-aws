package com.slow.book.springboot.config.auth;

import com.slow.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 1. Spring Security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // 2. h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
                .headers().frameOptions().disable()
                .and()
                // 3. URL 별 권한 관리를 설정하는 옵션의 시작점 이다. authorizeRequests 가 선언되어야만 antMatchers 옵션을 사용 할 수있다.
                .authorizeRequests()
                // 4-1. 권한 관리 대상을 지정하는 옵션, URL,HTTP 메소드별로 관리가 가능함, "/" 등 지정된 URL 들은 permitAll() 옵션을 통해 열람 권한을 주었음
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                // 4-2. "/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 가능하도록 했음.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 5. 설정된 값들 이외 나머지 URL 들을 나타낸다. 여기서는 authenticated() 을 추가하여 나머지 URL 들은 모두 인증된 사용자들(로그인한 사용자)에게만 허용하게 함
                .anyRequest().authenticated()
                .and()
                .logout()
                // 6. 로그아웃 기능에 대한 여러 서절의 진입점, 로그아웃 성공 시 / 주소로 이동함.
                .logoutSuccessUrl("/")
                .and()
                // 7. OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // 8. OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 암당함.
                .userInfoEndpoint()
                /*
                 9. 소셜 로그인 성공 시 후속 조치를 진행할 userService 인터페이스의 구현체를 등록함,
                 리소스 서버(즉 , 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 할 수 있음.
                 */
                .userService(customOAuth2UserService);
    }
}
