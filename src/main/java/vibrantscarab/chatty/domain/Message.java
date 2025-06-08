package vibrantscarab.chatty.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Author is required")
    private String author;
    @NotBlank(message = "Content is required")
    private String content;
    private LocalDateTime postedAt = LocalDateTime.now();
}
