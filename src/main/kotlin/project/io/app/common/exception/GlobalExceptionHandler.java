package project.io.app.common.exception;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
            .body(new ErrorResponse());
    }

    private static class ErrorResponse {

        public int getCode() {
            return 404;
        }

        public String getMessage() {
            return "URL을 찾을 수 없습니다.";
        }
    }
}
