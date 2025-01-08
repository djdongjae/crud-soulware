package kr.soulware.crudsoulware.reply.entity;

import jakarta.persistence.*;
import kr.soulware.crudsoulware.common.entity.BaseEntity;
import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", nullable = false)
    private Posts posts;

    @Builder
    public Reply(String content, User author, Posts posts) {
        this.content = content;
        this.author = author;
        this.posts = posts;
    }
}
