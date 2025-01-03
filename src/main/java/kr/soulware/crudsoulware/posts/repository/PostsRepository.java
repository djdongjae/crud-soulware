package kr.soulware.crudsoulware.posts.repository;

import kr.soulware.crudsoulware.posts.entity.Posts;
import kr.soulware.crudsoulware.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByAuthor(User user);
}
