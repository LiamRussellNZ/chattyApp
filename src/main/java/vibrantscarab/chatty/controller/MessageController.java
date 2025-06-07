package vibrantscarab.chatty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vibrantscarab.chatty.domain.Message;
import vibrantscarab.chatty.repository.MessageRepository;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository repository;

    @PostMapping
    public ResponseEntity<?> postMessage(@RequestBody Message message) {
        logger.info("POST /messages called by {} with content {}", message.getAuthor(), message.getContent());
        if (message.getAuthor() ==null | message.getContent() == null) {
            return ResponseEntity.status(401).body("Ensure your message has an author and content");
        }
        Message saved = repository.save(message);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(saved);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        logger.info("GET /messages called");
        return repository.findAll();
    }
}
