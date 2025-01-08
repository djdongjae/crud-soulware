package kr.soulware.crudsoulware.reply.repository;

import kr.soulware.crudsoulware.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
