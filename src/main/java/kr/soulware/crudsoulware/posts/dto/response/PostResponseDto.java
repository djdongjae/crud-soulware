package kr.soulware.crudsoulware.posts.dto.response;

import kr.soulware.crudsoulware.posts.entity.Posts;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public static PostResponseDto from(Posts posts) {
        return new PostResponseDto(posts.getId(), posts.getTitle(), posts.getContent(), posts.getAuthor());
    }
}
