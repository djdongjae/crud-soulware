package kr.soulware.crudsoulware.posts.service;

import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.NotFoundException;
import kr.soulware.crudsoulware.posts.dto.request.PostsSaveRequestDto;
import kr.soulware.crudsoulware.posts.dto.request.PostsUpdateRequestDto;
import kr.soulware.crudsoulware.posts.dto.response.PostResponseDto;
import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = findPostsById(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public PostResponseDto find(Long id) {
        Posts posts = findPostsById(id);
        return PostResponseDto.from(posts);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        List<Posts> postsList = postsRepository.findAll();
        return postsList.stream().map(PostResponseDto::from).toList();
    }

    @Transactional
    public Long delete(Long id) {
        Posts posts = findPostsById(id);
        postsRepository.delete(posts);
        return id;
    }

    private Posts findPostsById(Long postsId) {
        return postsRepository.findById(postsId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_POST_EXCEPTION,
                        ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()));
    }
}
