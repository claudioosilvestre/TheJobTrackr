package jobtrackr_api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Object> handleCompanyNotFoundException(
            CompanyNotFoundException ex, WebRequest request) {

        Map<String, Object> body = createErrorBody(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest webRequest) {

        Map<String, Object> body = createErrorBody(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<Object> handleCompanyAlreadyExistsException(
            CompanyAlreadyExistsException ex, WebRequest webRequest) {

        Map<String, Object> body = createErrorBody(HttpStatus.CONFLICT, ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }


    private Map<String, Object> createErrorBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return body;
    }
}
