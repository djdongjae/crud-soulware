package kr.soulware.crudsoulware.posts.repository;

import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByAuthor(User user);

    @Query("""
            SELECT posts
            FROM Posts posts LEFT JOIN FETCH posts.replyList
            WHERE posts.id = :id""")
    Optional<Posts> findWithRepliesById(Long id);
}
