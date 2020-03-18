package com.slow.book.springboot.service.posts;

import com.slow.book.springboot.domain.posts.Posts;
import com.slow.book.springboot.domain.posts.PostsRepository;
import com.slow.book.springboot.web.dto.PostsListResponseDto;
import com.slow.book.springboot.web.dto.PostsResponseDto;
import com.slow.book.springboot.web.dto.PostsSaveRequestDto;
import com.slow.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import  org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
        /*
            JpaRepository 에서 이미 delete 메소드를 지원하고 있으니 이를 활용한다. Entity 를 파라미터로 삭제할 수 도있고, deleteById 메소드를 이용하면 id 로 삭제할수도있다.
            존재하는 Posts 인지 확인을 위해 Entity 조회 후 그대로 삭제한다.
         */
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
