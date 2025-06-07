package vibrantscarab.chatty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vibrantscarab.chatty.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
