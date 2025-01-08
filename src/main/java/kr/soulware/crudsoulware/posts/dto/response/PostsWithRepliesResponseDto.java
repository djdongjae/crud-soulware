package kr.soulware.crudsoulware.posts.dto.response;

import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.reply.dto.response.ReplyResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostsWithRepliesResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private List<ReplyResponseDto> replies;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static PostsWithRepliesResponseDto from(Posts posts) {
        List<ReplyResponseDto> replies = posts.getReplyList().stream()
                .map(ReplyResponseDto::from)
                .toList();

        return new PostsWithRepliesResponseDto(
                posts.getId(),
                posts.getTitle(),
                posts.getContent(),
                posts.getAuthor().getName(),
                replies,
                posts.getCreateAt(),
                posts.getUpdateAt()
        );
    }
}
