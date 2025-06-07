package vibrantscarab.chatty.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id @GeneratedValue
    private Long id;
    private String author;
    private String content;
    private LocalDateTime postedAt = LocalDateTime.now();
}
