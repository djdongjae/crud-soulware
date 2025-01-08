package kr.soulware.crudsoulware.reply.dto.response;

import kr.soulware.crudsoulware.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyResponseDto {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static ReplyResponseDto from(Reply reply) {
        return new ReplyResponseDto(
                reply.getId(),
                reply.getContent(),
                reply.getAuthor().getName(),
                reply.getCreateAt(),
                reply.getUpdateAt()
        );
    }
}
