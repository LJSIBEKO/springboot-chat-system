package linda.co.za.chat.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;


@RestControllerAdvice
public class AppExceptionHandler
{
   @ExceptionHandler(RequestException.class)
   public ResponseEntity<ErrorResponse> handleRequestException(Exception ex, WebRequest request)
   {
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request)
   {
      List<String> errors = ex.getBindingResult().getFieldErrors()
              .stream().map(FieldError::getDefaultMessage).toList();
      ErrorResponse errorResponse = new ErrorResponse("Validation Failed",errors, request.getDescription(false), LocalDateTime.now());
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request)
   {
      ex.printStackTrace();
      ErrorResponse errorResponse = new ErrorResponse("A system error occurred while performing your function", request.getDescription(false), LocalDateTime.now());
      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(NoResourceFoundException.class)
   public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, WebRequest request){
      ErrorResponse errorResponse = new ErrorResponse("Not found", request.getDescription(false), LocalDateTime.now());
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
}
