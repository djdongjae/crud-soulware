package kr.soulware.crudsoulware.posts.repository;

import kr.soulware.crudsoulware.posts.entity.Posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 불러오기")
    public void createAndGetPost() {
//        // given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        postsRepository.save(Posts.builder()
//                .title(title)
//                .content(content)
//                        .author()
//                .build());
//
//        // when
//        List<Posts> postsList = postsRepository.findAll();
//
//        // then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
    }
}
