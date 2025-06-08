package vibrantscarab.chatty.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        boolean authorMissing = errors.contains("Author is required");
        boolean contentMissing = errors.contains("Content is required");

        if (authorMissing && contentMissing) {
            return ResponseEntity.badRequest().body("Author and content are required");
        } else {
            return ResponseEntity.badRequest().body(String.join("; ", errors));
        }
    }
}
