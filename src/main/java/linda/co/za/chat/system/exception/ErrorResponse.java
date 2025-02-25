package linda.co.za.chat.system.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse
{
    private String message;
    private String details;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, String details, LocalDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
    public ErrorResponse(String message,List<String> errors, String details, LocalDateTime timestamp) {
        this.message = message;
        this.errors = errors;
        this.details = details;
        this.timestamp = timestamp;
    }
}
