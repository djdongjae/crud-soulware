package kr.soulware.crudsoulware.posts.repository;

import kr.soulware.crudsoulware.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
