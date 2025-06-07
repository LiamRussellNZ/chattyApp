package vibrantscarab.chatty.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import vibrantscarab.chatty.domain.Message;
import vibrantscarab.chatty.repository.MessageRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MessageRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void postCallShouldReturnSavedMessages() throws Exception {
        Message saved = new Message();
        saved.setId(1L);
        saved.setAuthor("Matt Murdock");
        saved.setContent("I'm a really good lawyer");
        saved.setPostedAt(saved.getPostedAt());

        when(repository.save(any(Message.class))).thenReturn(saved);

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("Matt Murdock"))
                .andExpect(jsonPath("$.content").value("I'm a really good lawyer"));
    }

    @Test
    void getCallShouldReturnAllMessagesInTheRepository() throws Exception {
        Message mess1 = new Message();
        mess1.setId(1L);
        mess1.setAuthor("Wilson Fisk");
        mess1.setContent("New York belongs to me");

        Message mess2 = new Message();
        mess2.setId(2L);
        mess2.setAuthor("Spider-man");
        mess2.setContent("I'm your neighbour hood friendly Spider-man");

        when(repository.findAll()).thenReturn(List.of(mess1, mess2));

        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].author").value("Wilson Fisk"))
                .andExpect(jsonPath("$[0].content").value("New York belongs to me"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].author").value("Spider-man"))
                .andExpect(jsonPath("$[1].content").value("I'm your neighbour hood friendly Spider-man"));
    }
}