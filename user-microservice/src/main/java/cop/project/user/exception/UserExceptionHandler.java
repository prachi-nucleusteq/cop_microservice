package cop.project.user.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cop.project.user.model.ErrorResponse;


@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(final NotFoundException notFoundException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(notFoundException.getMessage());

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.toString(), errorList);

        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }

}
