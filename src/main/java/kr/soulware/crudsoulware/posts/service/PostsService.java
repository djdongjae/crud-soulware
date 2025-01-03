package kr.soulware.crudsoulware.posts.service;

import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.NotFoundException;
import kr.soulware.crudsoulware.exception.model.UnauthorizedException;
import kr.soulware.crudsoulware.posts.dto.request.PostsSaveRequestDto;
import kr.soulware.crudsoulware.posts.dto.request.PostsUpdateRequestDto;
import kr.soulware.crudsoulware.posts.dto.response.PostResponseDto;
import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.posts.repository.PostsRepository;
import kr.soulware.crudsoulware.security.UserDetailsImpl;
import kr.soulware.crudsoulware.user.entity.User;
import kr.soulware.crudsoulware.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto, UserDetailsImpl loginUser) {
        Posts posts = generatePosts(requestDto, loginUser);
        return postsRepository.save(posts).getId();
    }

    private Posts generatePosts(PostsSaveRequestDto requestDto, UserDetailsImpl loginUser) {
        User user = findUserByEmail(loginUser.getEmail());
        return Posts.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(user)
                .build();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto, UserDetailsImpl loginUser) {
        Posts posts = findPostsById(id);
        checkAuthor(posts, loginUser);
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
    public Long delete(Long id, UserDetailsImpl loginUser) {
        Posts posts = findPostsById(id);
        checkAuthor(posts, loginUser);
        postsRepository.delete(posts);
        return id;
    }

    private Posts findPostsById(Long postsId) {
        return postsRepository.findById(postsId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_POST_EXCEPTION,
                        ErrorCode.NOT_FOUND_POST_EXCEPTION.getMessage()
                ));
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION,
                        ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));
    }

    private void checkAuthor(Posts posts, UserDetailsImpl loginUser) {
        if (!posts.getAuthor().getEmail().equals(loginUser.getEmail())) {
            throw new UnauthorizedException(
                    ErrorCode.INSUFFICIENT_AUTHENTICATION_TO_POSTS,
                    ErrorCode.INSUFFICIENT_AUTHENTICATION_TO_POSTS.getMessage()
            );
        }
    }
}
