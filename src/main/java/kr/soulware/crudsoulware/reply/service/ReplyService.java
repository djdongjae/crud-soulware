package kr.soulware.crudsoulware.reply.service;

import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.NotFoundException;
import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.posts.repository.PostsRepository;
import kr.soulware.crudsoulware.reply.dto.request.ReplySaveRequestDto;
import kr.soulware.crudsoulware.reply.entity.Reply;
import kr.soulware.crudsoulware.reply.repository.ReplyRepository;
import kr.soulware.crudsoulware.security.UserDetailsImpl;
import kr.soulware.crudsoulware.user.entity.User;
import kr.soulware.crudsoulware.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    public Long save(ReplySaveRequestDto requestDto, Long postsId, UserDetailsImpl loginUser) {
        Posts posts = findPostsById(postsId);
        User author = findUserByEmail(loginUser.getEmail());
        Reply reply = generateReply(requestDto, posts, author);

        return replyRepository.save(reply).getId();
    }

    private Reply generateReply(ReplySaveRequestDto requestDto, Posts posts, User author) {
        return Reply.builder()
                .content(requestDto.getContent())
                .author(author)
                .posts(posts)
                .build();
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
}
